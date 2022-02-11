package com.revature.portfolio.repositories;

import com.revature.portfolio.models.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepo extends CrudRepository<Developer, String> {

    Developer findByUsername(String Username);

}
