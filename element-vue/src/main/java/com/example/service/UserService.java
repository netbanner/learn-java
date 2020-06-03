package com.example.service;

import com.example.beans.User;
import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User findUser(String username){
        return userDao.findByName(username);
    }
}
