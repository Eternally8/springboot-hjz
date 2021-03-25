import com.alibaba.fastjson.JSON;
import com.hjz.Application;
import com.hjz.dao.UserDao;
import com.hjz.utils.mapstruct.CarDto;
import com.hjz.utils.mapstruct.CarMapper;
import com.hjz.utils.mapstruct.CarVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/3/25 17:34
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test1 {

    @Autowired
    private UserDao userDao;

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

    @Test
    public void testMapStruce2(){
        System.out.println(userDao.getUserById(6));
    }

}
