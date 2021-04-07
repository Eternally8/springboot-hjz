package com.hjz.utils.jsonPath;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import org.springframework.context.annotation.Bean;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/4/7 16:02
 */
@org.springframework.context.annotation.Configuration
public class JsonPathConfig {

    @Bean("JsonPathConf")
    public Configuration configuration() {
        return Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
    }


    public static void main(String[] args) {
        String a = "{\n" +
                "      \"name\" : \"john\",\n" +
                "      \"gender\" : \"male\"\n" +
                "   }";
        JSONObject d = JSONObject.parseObject(a);

//        ReadContext ctx = JsonPath.using(jsonPathConf).parse(d);
        ReadContext ctx = JsonPath.parse(d);
        String gender0 = ctx.read("$.name");
        System.out.println(gender0);
        String gender1 = ctx.read("$.name1");
        System.out.println(gender1);
    }

}
