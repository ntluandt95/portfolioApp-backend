package com.revature.portfolio.services;

import com.revature.portfolio.models.Project;
import com.revature.portfolio.models.Resume;
import com.revature.portfolio.repositories.ResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService{
    @Autowired
    ResumeRepo repo;

    @Override
    public Resume add(Resume resume) {
        return repo.save(resume);
    }

    @Override
    public Resume get(int id) {
        return repo.findById(id).orElse(new Resume());
    }

    @Override
    public List<Resume> getAll() {
        return (List<Resume>) repo.findAll();
    }

    @Override
    public Resume update(Resume resume) {
        return repo.save(resume);
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
