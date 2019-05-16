package com.chengredis.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author cheng
 * @Date 2019/5/13 22:15
 **/
@Data
public class User {

    private static final long serialVersionUID = -7714828483631293167L;
    private Integer id;
    private String username;
    private String password;
    private String role;
    private Date createdAt;
    private Date updatedAt;

    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

