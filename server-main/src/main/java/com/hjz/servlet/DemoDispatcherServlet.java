package com.hjz.servlet;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DemoDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String,Object> ioc = new HashMap<>();

    private Map<String,Method> handleMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //运行阶段，根据用户请求的URL自动分发
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(this.handleMapping.isEmpty()){
            resp.getWriter().write("404 no found!!!");
            return;
        }

        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replace(contextPath,"").replaceAll("/+","/");

        if(!this.handleMapping.containsKey(url)){
            resp.getWriter().write("404 no found!!!");
            return;
        }

        Method method = this.handleMapping.get(url);

        //---------------------
        //从IOC中拿到容器
//        String beanName = lowerFristCase(method.getDeclaringClass().getSimpleName());
//        Map<String,String[]> params = req.getParameterMap();
//        method.invoke(ioc.get(beanName), new Object[]{req,resp,params.get("name")[0]});

        //--------------------
        //获取方法的参数列表
        Class<?>[] parametersTypes = method.getParameterTypes();
        //获取请求参数
        Map<String,String[]> parameterMap = req.getParameterMap();
        //保存所有需要自动复制的参数值
        Object[] paramValues = new Object[parametersTypes.length];

        for (int i = 0; i < parametersTypes.length; i++) {
            Class parmeterType = parametersTypes[i];
            if(parmeterType == HttpRequest.class){
                paramValues[i] = req;
                continue;
            }
            else if(parmeterType == HttpResponse.class){
                paramValues[i] = resp;
                continue;
            }
            else if(parmeterType == String.class){
                for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                    String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]","").replaceAll(",\\s",",");
                    paramValues[i] = value;
                }
            }
        }

        String beanName = lowerFristCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName), paramValues);
    }

    @Override
    public void init(ServletConfig config) {
        //1:加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2:扫描所有相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        
        //3:初始化扫描的所有类，并且把它保存到相关的IOC容器中
        try {
            doInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //4:实现依赖注入
        doAutowired();

        //5:初始化HandlerMapping
        initHandlerMapping();

        System.out.println("spring is init!!!!!");
    }

    private void initHandlerMapping() {
        if(ioc.isEmpty()){
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            if(!clazz.isAnnotationPresent(DemoController.class)){
                continue;
            }

            String baseUrl = "";
            if(clazz.isAnnotationPresent(DemoReqMapping.class)){
                DemoReqMapping reqMapping = clazz.getAnnotation(DemoReqMapping.class);
                baseUrl = reqMapping.value();
            }

            //只认public方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(DemoReqMapping.class)){
                    continue;
                }

                DemoReqMapping demoReqMapping = method.getAnnotation(DemoReqMapping.class);
                String url = ("/" + baseUrl + "/" + demoReqMapping.value()).replaceAll("/+","/");
                handleMapping.put(url,method);

                System.out.println("mapper:" + url + "," + method);
            }

        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()){
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //获得某个类的所有声明的字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(DemoAutowired.class)){
                    continue;
                }

                DemoAutowired autowired = field.getAnnotation(DemoAutowired.class);
                String beanName = autowired.value().trim();

                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }

                //强制暴力访问，只要加注解
                field.setAccessible(true);

                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            
        }
    }

    private void doInstance() throws Exception {
        if(classNames.isEmpty()){
            return;
        }

        for (String className : classNames) {
            try {
                //拿到类
                Class<?> clazz = Class.forName(className);
                //实例化，把实例化的对象放在IOC容器中

                if(clazz.isAnnotationPresent(DemoController.class)){
                    Object instance = clazz.newInstance();
                    String beanName = lowerFristCase(clazz.getSimpleName());
                    ioc.put(beanName,instance);
                }else if(clazz.isAnnotationPresent(DemoService.class)){
                    //因为Service有可能注入的不是它本省，有可能是它的实现类

                    //1:默认类名首字母小写
                    //2：自定义的beanName
                    DemoService demoService = clazz.getAnnotation(DemoService.class);
                    String beanName = demoService.value();
                    if("".equals(beanName)){
                        beanName = lowerFristCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);
                    //3:如果是接口，投机取巧的方式，用它的的接口类型作为key
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("the beanName is exists");
                        }
                        ioc.put(i.getName(),instance);
                    }
                }else{
                    continue;
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    //首字母小写
    private String lowerFristCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            //如果是文件夹
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else{
                if(file.getName().endsWith(".class")){
                    //获取全类名
                    String className = (scanPackage + "." + file.getName()).replace(".class","");
                    classNames.add(className);
                }
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);

        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
