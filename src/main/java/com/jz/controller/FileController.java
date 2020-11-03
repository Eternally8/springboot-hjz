package com.jz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2020/8/5 19:48
 */
@Slf4j
@Api(tags = "文件处理")
@Controller
@RequestMapping("/file")
public class FileController {

    //通过Id下载文件信息记录
    @ApiOperation(value = "通过Id下载文件信息记录")
    @GetMapping("/downloadFileById")
    public void downloadFileById(@RequestParam int id, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/csv;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("file"+id+".csv", "UTF-8"));
        resp.setCharacterEncoding("UTF-8");

        String str = "aaa,bbb,ccc,ddd";
        String[] strMap = str.split(",");

        List<List<Object>> datalist = new ArrayList<List<Object>>();
        List<Object> data = null;
        for (String s : strMap) {
            data = new ArrayList<Object>();
            data.add(s);
            datalist.add(data);
        }

        createCSVFile(datalist,resp);
    }


    public static void createCSVFile(List<List<Object>> dataList, HttpServletResponse response) {
        BufferedWriter csvWtriter = null;
        try {
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "GB2312"), 1024);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        if(row == null){
            return;
        }
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

}
