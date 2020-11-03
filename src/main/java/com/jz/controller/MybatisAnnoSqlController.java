package com.jz.controller;

import com.alibaba.fastjson.JSON;
import com.jz.model.UserVoEntity;
import com.jz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "mybatis注解sql使用")
@RestController
@RequestMapping("/mybatis")
public class MybatisAnnoSqlController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "插入用户信息",notes = "插入用户信息详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value="用户名称",required = true),
            @ApiImplicitParam(name = "age",value="用户年龄",required = true),
            @ApiImplicitParam(name = "sex",value="性别",required = true)
    })
    @GetMapping("/insertUser")
    public UserVoEntity insertUser(String name, int age, boolean sex){
        log.info("getUser_name:{}",name);

        UserVoEntity vo = new UserVoEntity();
        vo.setName(name);
        vo.setAge(age);
        vo.setSex(sex);
        userService.insertUser(vo);
        log.info("插入用户信息为：{}", JSON.toJSONString(vo));
        return vo;
    }


    @ApiOperation(value = "获取单个用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value="id",required = true)
    })
    @GetMapping("/getUserById")
    public UserVoEntity getUserById(int id){
        return userService.getUserById(id);
    }


    @ApiOperation(value = "更新用户信息",notes = "会返回影响的行数")
    @PostMapping("/updateUser")
    public int updateUser(@RequestBody UserVoEntity vo){
        return userService.updateUser(vo);
    }


    @ApiOperation(value = "删除用户信息",notes = "会返回影响的行数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value="id",required = true)
    })
    @DeleteMapping("/delUserById")
    public int delUserById(int id){
        return userService.delUserById(id);
    }



    @ApiOperation(value = "获取用户信息2",notes = "测试filter使用")
    @RequestMapping(value = "/getUser2",method = RequestMethod.POST)
    public UserVoEntity getUser2(@RequestBody UserVoEntity reqVo){
        log.info("getUser2_reqVo:{}",reqVo);

        return reqVo;
    }

    @ApiOperation(value = "获取用户信息3",notes = "测试interceptor使用")
    @RequestMapping(value = "/getUser3",method = RequestMethod.POST)
    public UserVoEntity getUser3(String name){
        log.info("getUser3_name:{}",name);

        return null;
    }


    @ApiOperation(value = "错误页面",notes = "错误详细信息")
    @RequestMapping(value = "/failed",method = RequestMethod.POST)
    public Map<String,String> failed(){
        log.info("~~~failed~~~");

        Map<String,String> map = new HashMap<>();
        map.put("code","500");
        map.put("msg","fail");
        return map;
    }

}
