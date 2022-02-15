package com.revature.portfolio.services;

import com.revature.portfolio.models.Project;

import java.util.List;

public interface ProjectService {
    public Project add(Project project);
    public Project get(int id);
    public List<Project> getAll();
    public List<Project> getbyDeveloper(String username);
    public Project update(Project project);
    public boolean delete(int id);
}
