package com.revature.portfolio.controllers;


import com.revature.portfolio.PortfolioApplication;
import com.revature.portfolio.jwt.JwtTokenUtil;
import com.revature.portfolio.models.Project;
import com.revature.portfolio.models.Resume;
import com.revature.portfolio.services.ProjectService;
import com.revature.portfolio.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ResumeController {
    @Autowired
    ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @GetMapping("/Resumes")
    public List<Resume> getAll() {
        return service.getAll();
    }

    @GetMapping("/Resumes/{id}")
    public ResponseEntity<Resume> getResume(@PathVariable("id") String id) {
        Resume resume = service.get(Integer.parseInt(id));

        if(resume.getId() != 0) return new ResponseEntity<Resume>(resume, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/Resumes", consumes = "application/json", produces = "application/json")
    public Resume addResume(@RequestBody Resume resume, @RequestHeader("Authorization") String header) {
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(resume.getDevUsername().getUsername()) && !tokenUtil.getUsername(token).equals("admin"))
            return null;

        return service.add(resume);
    }

    @PutMapping(value = "/Resumes/{id}", consumes = "application/json", produces = "application/json")
    public Resume updateResume(@PathVariable("id") String id, @RequestBody Resume resume,
                               @RequestHeader("Authorization") String header) {
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(resume.getDevUsername().getUsername()) && !tokenUtil.getUsername(token).equals("admin"))
            return null;

        resume.setId(Integer.parseInt(id));
        return service.update(resume);
    }


    @DeleteMapping("/Resumes/{id}")
    public ResponseEntity<Project> deleteResume(@PathVariable("id") String id, @RequestHeader("Authorization") String header) {

        Resume resume = service.get(Integer.parseInt(id));
        // Get authorization header and validate
        final String token = header.split(" ")[1].trim();
        JwtTokenUtil tokenUtil = PortfolioApplication.app.getBean(JwtTokenUtil.class);
        if(token == null || !tokenUtil.getUsername(token).equals(resume.getDevUsername().getUsername()) && !tokenUtil.getUsername(token).equals("admin"))
            return new ResponseEntity<Project>(HttpStatus.UNAUTHORIZED);

        boolean success = service.delete(Integer.parseInt(id));
        return new ResponseEntity<>((success) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
