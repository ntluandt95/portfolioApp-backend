package com.revature.portfolio.repositories;

import com.revature.portfolio.models.Project;
import com.revature.portfolio.models.Resume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepo extends CrudRepository<Resume,Integer> {
    List<Resume> findByDevUsername(String username);
}
