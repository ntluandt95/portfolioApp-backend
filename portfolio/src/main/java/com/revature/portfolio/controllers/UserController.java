package com.revature.portfolio.controllers;

import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.User;
import com.revature.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService us;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return us.getAllUsers();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = us.getUser(username);

        if(user == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/users/{username}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable("username") String username,
                                                     @RequestBody User user) {

        // TODO Handle Authorization
        user.setUsername(username);
        User updated = us.updateUser(user);

        if(updated == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<User>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("username") String username){

        // TODO Handle Authorization
        boolean success = us.deleteUser(username);
        if(success)
            return new ResponseEntity<Boolean>(success, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<Boolean>(success, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/users", consumes = "application/json")
    public ResponseEntity<User> addDeveloper(@RequestBody User user) {

        // TODO handle collisions and authorization
        User added = us.addUser(user);
        if(added == null)
            return new ResponseEntity<User>(HttpStatus.SERVICE_UNAVAILABLE);
        else
            return new ResponseEntity<User>(added, HttpStatus.CREATED);
    }
}
