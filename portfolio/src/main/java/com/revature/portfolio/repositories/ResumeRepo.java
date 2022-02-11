package com.revature.portfolio.repositories;

import com.revature.portfolio.models.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepo extends CrudRepository<Resume,Integer> {
}
