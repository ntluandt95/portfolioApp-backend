package com.revature.portfolio.controllers;

import com.revature.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService us;

    
}
