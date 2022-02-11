package com.revature.portfolio.services;

import com.revature.portfolio.models.Resume;

import java.util.List;

public interface ResumeService {
    public Resume add(Resume resume);
    public Resume get(int id);
    public List<Resume> getAll();
    List<Resume> getbyDeveloper(String username);
    public Resume update(Resume resume);
    public boolean delete(int id);
}
