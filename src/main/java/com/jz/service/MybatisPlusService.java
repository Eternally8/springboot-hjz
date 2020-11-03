package com.jz.service;

import com.jz.dao.mybatisPlus.UserMbplusInfoMapper;
import com.jz.model.UserMbplusInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MybatisPlusService {


    @Autowired
    private UserMbplusInfoMapper userMbplusInfoMapper;

    public void insertUser(UserMbplusInfoEntity vo) {
        userMbplusInfoMapper.insert(vo);
    }

    public UserMbplusInfoEntity getUserById(Integer id) {
        return userMbplusInfoMapper.selectById(id);
    }


    public UserMbplusInfoEntity updateUser(UserMbplusInfoEntity vo) {
        int updateCount = userMbplusInfoMapper.updateById(vo);
        log.info("mybaits-plus更新的数量:{}",updateCount);

        return userMbplusInfoMapper.selectById(vo.getId());
    }



}
