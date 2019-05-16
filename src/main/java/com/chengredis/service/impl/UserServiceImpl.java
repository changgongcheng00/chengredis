package com.chengredis.service.impl;

import com.chengredis.dao.UserDao;
import com.chengredis.entity.User;
import com.chengredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author cheng
 * @Date 2019/5/13 22:16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> list() {
        return userDao.list();
    }
}

