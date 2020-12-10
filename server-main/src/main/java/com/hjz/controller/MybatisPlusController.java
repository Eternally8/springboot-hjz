package com.hjz.controller;

import com.hjz.model.UserMbplusInfoEntity;
import com.hjz.service.MybatisPlusService;
import com.hjz.utils.reqResult.ResponseEntityDto;
import com.hjz.utils.reqResult.UnifiedReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "mybatis-Plus使用")
@RestController
@RequestMapping("/mybatisPlus")
public class MybatisPlusController extends UnifiedReply {
    @Autowired
    private MybatisPlusService mybatisPlusService;


    @ApiOperation(value = "插入用户信息",notes = "插入用户信息详情")
    @PostMapping("/insertUser")
    public ResponseEntityDto insertUser(@RequestBody UserMbplusInfoEntity vo){
        mybatisPlusService.insertUser(vo);

        UserMbplusInfoEntity vo2 = mybatisPlusService.getUserById(vo.getId());

        return buildSuccesResp(vo2);
    }


    @ApiOperation(value = "更新用户信息",notes = "更新用户信息")
    @PostMapping("/updateUser")
    public ResponseEntityDto updateUser(@RequestBody UserMbplusInfoEntity vo){
        UserMbplusInfoEntity result = mybatisPlusService.updateUser(vo);
        return buildSuccesResp(result);
    }


}
