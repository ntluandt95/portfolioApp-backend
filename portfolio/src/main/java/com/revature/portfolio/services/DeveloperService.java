package com.revature.portfolio.services;

import com.revature.portfolio.models.Developer;

import java.util.List;

public interface DeveloperService {

    Developer addDeveloper(Developer d);
    Developer getDeveloper(String username);
    List<Developer> getAllDevelopers();
    Developer updateDeveloper(Developer change);
    boolean deleteDeveloper(String username);

    //List<Developer> getDeveloperByRole();
    List<Developer> getDeveloperByStatus(Developer.Status status);
}
