package com.revature.portfolio.controllers;

import com.revature.portfolio.PortfolioApplication;
import com.revature.portfolio.jwt.JwtTokenUtil;
import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.User;
import com.revature.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
            return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value="/users/{username}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable("username") String username,
                                                     @RequestBody User user, @RequestHeader("Authorization") String header) {
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(username) && !tokenUtil.getUsername(token).equals("admin"))
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);


        user.setUsername(username);
        User updated = us.updateUser(user);

        if(updated == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<User>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("username") String username,
                                              @RequestHeader("Authorization") String header){

        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(username) && !tokenUtil.getUsername(token).equals("admin"))
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);


        boolean success = us.deleteUser(username);
        if(success)
            return new ResponseEntity<Boolean>(success, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<Boolean>(success, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/users", consumes = "application/json")
    public ResponseEntity<User> addDeveloper(@RequestBody User user) {

        User added = us.addUser(user);
        if(added == null)
            return new ResponseEntity<User>(HttpStatus.SERVICE_UNAVAILABLE);
        else
            return new ResponseEntity<User>(added, HttpStatus.CREATED);
    }
}
