package com.revature.portfolio.services;

import com.revature.portfolio.models.Developer;
import com.revature.portfolio.repositories.DeveloperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImplementation implements DeveloperService{
    @Autowired
    DeveloperRepo repo;

    @Override
    public Developer addDeveloper(Developer d) {
        return repo.save(d);
    }

    @Override
    public Developer getDeveloper(String username) {
        return repo.findById(username).orElse(null);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return (List<Developer>) repo.findAll();
    }

    @Override
    public Developer updateDeveloper(Developer change) {
        return repo.save(change);
    }

    @Override
    public boolean deleteDeveloper(String username) {
        try {
            repo.deleteById(username);
            return true;
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
