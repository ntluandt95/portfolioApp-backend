package com.revature.portfolio.controllers;


import com.revature.portfolio.PortfolioApplication;
import com.revature.portfolio.jwt.JwtTokenUtil;
import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.Project;
import com.revature.portfolio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService service;

    public ProjectController(ProjectService service){
        this.service = service;
    }

    @GetMapping("/Projects")
    public List<Project> getAll() {
        return service.getAll();
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") String id) {
        Project project = service.get(Integer.parseInt(id));

        if(project.getId() != 0) return new ResponseEntity<Project>(project, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/Projects", consumes = "application/json", produces = "application/json")
    public Project addProject(@RequestBody Project project, @RequestHeader("Authorization") String header) {
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(project.getDevUsername().getUsername()) && !tokenUtil.getUsername(token).equals("admin"))
            return null;

        return service.add(project);
    }

    @PutMapping(value = "/Projects/{id}", consumes = "application/json", produces = "application/json")
    public Project updateProject(@PathVariable("id") String id, @RequestBody Project project,
                                 @RequestHeader("Authorization") String header) {
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(project.getDevUsername().getUsername()) && !tokenUtil.getUsername(token).equals("admin"))
            return null;

        project.setId(Integer.parseInt(id));
        return service.update(project);
    }


    @DeleteMapping("/Projects/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") String id,
                                                 @RequestHeader("Authorization") String header) {
        Project project = service.get(Integer.parseInt(id));

        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(project.getDevUsername().getUsername()) && !tokenUtil.getUsername(token).equals("admin"))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        boolean success = service.delete(Integer.parseInt(id));
        return new ResponseEntity<>((success) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

}
