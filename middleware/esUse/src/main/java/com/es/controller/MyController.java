package com.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.es.config.ESConfig;
import com.es.model.SearchParam;
import com.es.service.MyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.zip.ZipOutputStream;

@Api(tags = "ElasticSearch")
@RestController
@RequestMapping("/es")
public class MyController {

    @Autowired
    private MyService service;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Autowired
    private RestHighLevelClient rClient;
    @Autowired
    private ESConfig eSConfig;

    @PostMapping("/search1")
    @ApiOperation("根据id查询ES对应的数据")
    public void searchMatch(String key, String value) throws Exception {
        SearchRequest searchRequest = new SearchRequest("risk-20210330");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchQuery(key,value));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = eSConfig.getClient().search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));

        SearchHit[] hits = response.getHits().getHits();
    }


    @PostMapping("/search")
    @ApiOperation("根据id查询ES对应的数据")
    public JSONObject getDataById(@RequestBody SearchParam param) {
        return service.getDataById(param);
    }

    @PostMapping("/add")
    @ApiOperation("往ES里插入数据")
    public ResponseEntity add(@RequestBody SearchParam param) {
        return service.add(param);
    }

    @PostMapping("/update")
    @ApiOperation("根据id更新文档内容")
    public ResponseEntity update(@RequestBody SearchParam param) {
        return service.update(param);
    }

    @PostMapping("/delete")
    @ApiOperation("根据id更新文档内容")
    public ResponseEntity delete(@RequestBody SearchParam param) {
        return service.delete(param);
    }


    public static void main(String[] args) throws IOException {
        String taskId = "asdfasdfasdf";
        File tempDir = createTempDir(taskId);
        File srcZipFile = new File(tempDir, taskId + ".zip");
        ZipOutputStream zipOut = createZipOut(srcZipFile);

        File file = new File(taskId+".job");
        // FileWriter writer = new FileWriter(file, true);
        BufferedWriter writer =new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"));
        System.out.println(1111);
    }

    public static File createTempDir(final String parent) throws IOException {
        final File tempDir = new File(System.getProperty("java.io.tmpdir"), parent);
        if (tempDir.exists() && tempDir.isDirectory()) {
//            FileUtils.deleteDirectory(tempDir);
        }else{
            tempDir.mkdir();
            tempDir.deleteOnExit();
        }
        return tempDir;
    }

    public static ZipOutputStream createZipOut(final File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
        return out;
    }

}