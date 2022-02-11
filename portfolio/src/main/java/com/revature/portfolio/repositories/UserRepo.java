package com.revature.portfolio.repositories;

import com.revature.portfolio.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
}
