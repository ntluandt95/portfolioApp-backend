package com.revature.portfolio.services;

import com.revature.portfolio.models.User;
import com.revature.portfolio.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepo repo;

    @Override
    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public User getUser(String username) {
        return repo.findById(username).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) repo.findAll();
    }

    @Override
    public User updateUser(User change) {
        return repo.save(change);
    }

    @Override
    public boolean deleteUser(String username) {
        try{
            repo.deleteById(username);
            return true;
        }catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
