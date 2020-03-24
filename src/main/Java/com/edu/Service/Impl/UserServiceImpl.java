package com.edu.Service.Impl;

import com.edu.Service.UserService;
import com.edu.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User login(List<User> userList, User user) {
        for (User user1 : userList){
            if (user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword()))
                return user1;
        }
        return null;
    }
}
