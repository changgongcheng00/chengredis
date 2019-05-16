package com.chengredis.controller;

import com.chengredis.entity.User;
import com.chengredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CityController
 * @Description TODO
 * @Author cheng
 * @Date 2019/5/13 22:36
 **/
@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public List<User> index(){
        return userService.list();
    }
}

