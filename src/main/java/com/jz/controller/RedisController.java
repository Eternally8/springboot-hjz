package com.jz.controller;

import com.jz.config.RedisConfig.RedisMQChannels;
import com.jz.model.UserVoEntity;
import com.jz.service.UserService;
import com.jz.utils.RedisUtils;
import com.jz.utils.reqResult.ResponseEntityDto;
import com.jz.utils.reqResult.UnifiedReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "redis使用")
@RestController
@RequestMapping("/redis")
public class RedisController extends UnifiedReply {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation(value = "redis注解缓存",notes = "可自定义缓存失效时间和key生成器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value="id",required = true)
    })
    @GetMapping(value = "/getUser")
    public ResponseEntityDto<UserVoEntity> getUser(int id){
        userService.getUserByRedis(id);
        userService.getUserByRedisValue(id);
        userService.getUserByRedisTime(id);
        return buildSuccesResp();
    }

    //接受消息的方法见com.jz.redisMsg.RCMHandler
    @ApiOperation(value = "测试redis发布订阅消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value="消息内容",required = true)
    })
    @GetMapping(value = "/sendRedisMsg")
    public String sendRedisMsg(String msg){
        redisUtils.convertAndSend(RedisMQChannels.redisChannelTest1,msg);
        redisUtils.convertAndSend(RedisMQChannels.redisChannelTest2,msg);

        return msg;
    }


}
