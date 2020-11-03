package com.jz.service;

import com.jz.dao.UserDao;
import com.jz.model.UserVoEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableCaching
public class UserService {

    @Autowired
    private UserDao userDao;

    public void insertUser(UserVoEntity vo) {
         userDao.insertUser(vo);
    }

    public UserVoEntity getUserById(int id) {
        return userDao.getUserById(id);
    }

    public int updateUser(UserVoEntity vo) {
        return userDao.updateUser(vo);
    }

    public int delUserById(int id) {
        return userDao.delUserById(id);
    }

    @Cacheable(value = "UserService_getUserByRedisTime#s#100")
    public UserVoEntity getUserByRedisTime(int id) {
        return userDao.getUserById(id);
    }

    @Cacheable(value = "UserService_getUserByRedisValue")
    public UserVoEntity getUserByRedisValue(int id) {
        return userDao.getUserById(id);
    }

    //目前缓存必须带value
    @Cacheable(value = "test",keyGenerator = "cacheKeyGenerator")
    public UserVoEntity getUserByRedis(int id) {
        return userDao.getUserById(id);
    }

}
