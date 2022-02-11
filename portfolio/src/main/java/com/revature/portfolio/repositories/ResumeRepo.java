package com.revature.portfolio.repositories;

import com.revature.portfolio.models.Project;
import com.revature.portfolio.models.Resume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResumeRepo extends CrudRepository<Resume,Integer> {
    List<Resume> findByDevUsername(String username);
}
