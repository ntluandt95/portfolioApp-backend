package com.revature.portfolio.services;

import com.revature.portfolio.models.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    User getUser(String username);
    List<User> getAllUsers();
    User updateUser(User change);
    boolean deleteUser(String username);

}
