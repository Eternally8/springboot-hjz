package com.hjz.utils;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/2/7 21:02
 */
@Slf4j
public class FileTools {


    public static boolean upload(MultipartFile file, String filePath){
        try {
            File f = multipartFileToFile(file);
            String result = HttpRequest.post("ConstantMp.cloudServerPath")
                    .header(Header.CONTENT_TYPE, "multipart/form-data; boundary=<calculated when request is sent>")
                    .header(Header.AUTHORIZATION,"Basic *****")
                    .form("file",f)
                    .form("filePath",filePath)
                    .execute().body();
            log.info("上传云端文件filePath:{}结果:{} ",filePath,result);
            f.delete();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传云端文件filePath:{}异常:",filePath,e);
            return false;
        }
        return true;
    }


    //MultipartFile转File(此方法会生成临时文件,完事后需要手动删除文件 f.delete())
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
