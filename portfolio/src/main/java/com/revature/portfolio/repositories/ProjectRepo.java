package com.revature.portfolio.repositories;

import com.revature.portfolio.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends CrudRepository<Project,Integer>{
    List<Project> findByDevUsername(String username);
}
