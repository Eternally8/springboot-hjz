package com.jz.controller;

import cn.hutool.core.io.FileUtil;
import com.jz.utils.reqResult.ResponseEntityDto;
import com.jz.utils.reqResult.UnifiedReply;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
public class FileController extends UnifiedReply {

    @ApiOperation(value = "上传文件", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping
    public ResponseEntityDto<?> createPackage(@ApiParam("文件名") @RequestParam("fileName") String fileName,
                                              @ApiParam("文件描述") @RequestParam("fileDesc") String fileDesc,
                                              @ApiParam(name = "file") @RequestPart("file") MultipartFile file) {
        // 处理上传逻辑
        log.info("文件大小为:{}",file.getSize());
        return buildSuccesResp(file.getSize());
    }


    @ApiOperation(value = "下载cvs文件",notes = "根据id下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value="id",required = true)
    })
    @GetMapping("/downLoadColdData")
    public ResponseEntity<?> downLoadColdData(String id){
        log.info("下载cvs文件:{}",id);
        String path = "/root/temp/" + id + ".csv";

        //根据路径 api返回文件
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=" + FileUtil.newFile(path).getName());
        headers.add("filename", FileUtil.newFile(path).getName());

        InputStreamResource resource = new InputStreamResource(FileUtil.getInputStream(path));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(FileUtil.file(path).length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }


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
