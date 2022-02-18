package com.revature.portfolio.controllers;

import com.revature.portfolio.PortfolioApplication;
import com.revature.portfolio.jwt.JwtTokenUtil;
import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.Project;
import com.revature.portfolio.models.Resume;
import com.revature.portfolio.services.DeveloperService;
import com.revature.portfolio.services.ProjectService;
import com.revature.portfolio.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class DeveloperController {

    @Autowired
    DeveloperService ds;
    @Autowired
    ResumeService rs;
    @Autowired
    ProjectService ps;

    @GetMapping("/developers")
    public List<Developer> getAllDevelopers() {
        return ds.getAllDevelopers();
    }

    @GetMapping("/developers/{username}")
    public ResponseEntity<Developer> getDeveloper(@PathVariable("username") String username) {
        Developer developer = ds.getDeveloper(username);

        if(developer == null)
            return new ResponseEntity<Developer>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Developer>(developer, HttpStatus.OK);
    }

    @GetMapping("/developers/{username}/resumes")
    public List<Resume> getDeveloperResumes(@PathVariable("username") String username) {
        return rs.getbyDeveloper(username);
    }

    @GetMapping("/developers/{username}/projects")
    public List<Project> getDeveloperProjects(@PathVariable("username") String username) {
        return ps.getbyDeveloper(username);
    }

    @PutMapping(value="/developers/{username}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable("username") String username,
                                                     @RequestBody Developer developer,
                                                     @RequestHeader("Authorization") String header) {
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(username) && !tokenUtil.getUsername(token).equals("admin"))
            return new ResponseEntity<Developer>(HttpStatus.UNAUTHORIZED);

        developer.setUsername(username);
        Developer updated = ds.updateDeveloper(developer);

        if(updated == null)
            return new ResponseEntity<Developer>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Developer>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/developers/{username}")
    public ResponseEntity<Boolean> deleteMove(@PathVariable("username") String username,
                                              @RequestHeader("Authorization") String header) {
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(username) && !tokenUtil.getUsername(token).equals("admin"))
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);


        boolean success = ds.deleteDeveloper(username);
        if(success)
            return new ResponseEntity<Boolean>(success, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<Boolean>(success, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/developers", consumes = "application/json")
    public ResponseEntity<Developer> addDeveloper(@RequestBody Developer developer){

        // Get authorization header and validate
//        final String token = header.split(" ")[1].trim();
//        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
//        if(token == null || !tokenUtil.getUsername(token).equals(developer.getUsername()) && !tokenUtil.getUsername(token).equals("admin"))
//            return new ResponseEntity<Developer>(HttpStatus.UNAUTHORIZED);
        System.out.println(developer.toString());
        Developer added = ds.addDeveloper(developer);
        if(added == null)
            return new ResponseEntity<Developer>(HttpStatus.SERVICE_UNAVAILABLE);
        else
            return new ResponseEntity<Developer>(added, HttpStatus.CREATED);
    }

    @GetMapping(value = "/search/{searchString}")
    public List<Developer> search(@PathVariable("searchString") String searchString) {
        return ds.getAllDevelopers().stream().filter(element -> element.toString().contains(searchString)).collect(Collectors.toList());
    }

}
