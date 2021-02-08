package com.hjz.utils.apolloApi;

import cn.hutool.setting.dialect.Props;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.core.utils.StringUtils;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenAppNamespaceDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/2/8 13:36
 */
@Slf4j
@Component
public class ApolloInit {

    //path为apolloInit目录路径,下层有 命名空间/具体配置文件
    public void start(String path) throws Exception{
        File apolloFilePath = ResourceUtils.getFile(path);

        if(apolloFilePath.exists()){
            File[] nameSpacesList = apolloFilePath.listFiles();

            for(File childFile : nameSpacesList){
                if(!childFile.isDirectory()){
                    continue;
                }
                String appId = childFile.getName();

                File[] fileList = childFile.listFiles();
                Map<String,Boolean> nameSpaceMap = getNameSpaceMap(appId);

                for(File file:fileList){
                    log.info("########################################################");
                    log.info("开始处理文件:" + file.getName());

                    //处理命名空间
                    String nameSpace = file.getName().split("\\+")[2].split("\\.")[0];
                    if(nameSpaceMap.get(nameSpace) == null){
                        addNameSpace(appId,nameSpace);
                    }

                    Props props = new Props(file);
                    for (Object o : props.keySet()) {
                        handleFields(appId,nameSpace,o,props);
                    }

                    publishNameSpace(appId,nameSpace);
                }
            }
        }
    }


    private void publishNameSpace(String appId, String nameSpace) {
        NamespaceReleaseDTO d = new NamespaceReleaseDTO();
        d.setEmergencyPublish(false);
        d.setReleaseComment("just publish");
        d.setReleasedBy("apollo");
        d.setReleaseTitle("init");
        ApolloTool.getClient().publishNamespace(appId,"DEV","default",nameSpace,d);
    }


    private void addNameSpace(String appId, String n) {
        OpenAppNamespaceDTO d = new OpenAppNamespaceDTO();
        d.setAppId(appId);
        d.setPublic(false);
        d.setFormat("properties");
        d.setAppendNamespacePrefix(false);
        d.setName(n);
        d.setComment(n + " comment");
        d.setDataChangeCreatedBy("apollo");
        d.setDataChangeCreatedTime(new Date());
        d.setDataChangeLastModifiedBy("apollo");
        d.setDataChangeLastModifiedTime(new Date());
        log.info("{} 命名空间不存在,开始增加!",n);
        OpenAppNamespaceDTO o = ApolloTool.getClient().createAppNamespace(d);
        if(o != null){
            log.info("{} 命名空间增加成功!{}",n, JSON.toJSONString(o));
        }
    }


    private void handleFields(String appId, String nameSpace, Object o, Props props) {
        if(o == null || StringUtils.isEmpty(o.toString())){
            return;
        }
        OpenItemDTO item = new OpenItemDTO();
        item.setKey(o.toString());
        if(props.get(o) != null){
            item.setValue(props.get(o).toString());
        }else{
            item.setValue(null);
        }
        item.setDataChangeCreatedBy("apollo");
        item.setDataChangeCreatedTime(new Date());
        item.setDataChangeLastModifiedBy("apollo");
        item.setDataChangeLastModifiedTime(new Date());
        log.info("开始更新key~{}:{}",o.toString(),props.get(o).toString());
        ApolloTool.getClient().createOrUpdateItem(appId,"DEV","default", nameSpace, item);
        log.info("开始更新key~{}成功",o.toString());
    }


    private Map<String, Boolean> getNameSpaceMap(String appId) {
        Map<String,Boolean> map = new HashMap<>();
        List<OpenNamespaceDTO> list = ApolloTool.getClient().getNamespaces(appId,"DEV","default");
        for (OpenNamespaceDTO openNamespaceDTO : list) {
            map.put(openNamespaceDTO.getNamespaceName(),true);
        }

        return map;
    }


}