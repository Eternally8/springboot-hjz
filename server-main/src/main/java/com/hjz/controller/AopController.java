package com.hjz.controller;

import com.hjz.example.aop.DeclareParents.Animal;
import com.hjz.example.aop.DeclareParents.Person;
import com.hjz.example.aop.DeclareParents.Women;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "Aop测试")
@RestController
@RequestMapping("/aop")
public class AopController {
    @Autowired
    private Women women;

    @ApiOperation(value = "测试springAop使用",notes = "测试springAop使用")
    @PostMapping(value = "/getUser1")
    public String getUser1(String name){
        log.info("getUser1_name:{}",name);

        return name;
    }

    @ApiOperation(value = "测试@DeclareParents使用")
    @RequestMapping(value = "/getUser2",method = RequestMethod.POST)
    public String getUser2(String name){
        log.info("getUser2_name:{}",name);

        Person person = women;
        person.likePerson();
        Animal animal = (Animal)person;
        animal.eat();

        women.likePerson();
        Animal animal2 = (Animal)women;
        animal2.eat();

        return name;
    }


}
