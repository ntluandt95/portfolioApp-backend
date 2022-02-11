package com.revature.portfolio.services;

import com.revature.portfolio.models.Project;
import com.revature.portfolio.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectRepo repo;
    @Override
    public Project add(Project project) {
        return repo.save(project);
    }

    @Override
    public Project get(int id) {
        return repo.findById(id).orElse(new Project());
    }

    @Override
    public List<Project> getAll() {
        return (List<Project>) repo.findAll();
    }

    @Override
    public List<Project> getbyDeveloper(String username) {
        return repo.findByDevUsername(username);
    }

    @Override
    public Project update(Project project) {
        return repo.save(project);
    }

    @Override
    public boolean delete(int id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }

    }
}
