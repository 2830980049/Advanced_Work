package com.edu.Service;

import com.edu.domain.User;

import java.util.List;

public interface UserService {

    public User login(List<User> userList,User user);
}
