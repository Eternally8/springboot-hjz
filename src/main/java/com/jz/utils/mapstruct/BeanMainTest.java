package com.jz.utils.mapstruct;

import com.alibaba.fastjson.JSON;
import com.jz.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2020/8/14 11:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanMainTest {

    @Test
    public void testMapStruce(){
        CarDto carDto = new CarDto();
        carDto.setId(1);
        carDto.setColor("红色");
        carDto.setName("tsl");
        carDto.setCreateTime(new Date());

        CarVo carVo = CarMapper.INSTANCE.carDtoToCarVo(carDto);
        System.out.println(JSON.toJSONString(carVo));
    }

}
