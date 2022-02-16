package com.revature.portfolio.controllers;


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
    public Resume addResume(@RequestBody Resume resume) {
        return service.add(resume);
    }

    @PutMapping(value = "/Resumes/{id}", consumes = "application/json", produces = "application/json")
    public Resume updateResume(@PathVariable("id") String id, @RequestBody Resume resume) {

        resume.setId(Integer.parseInt(id));
        return service.update(resume);
    }


    @DeleteMapping("/Resumes/{id}")
    public ResponseEntity<Project> deleteResume(@PathVariable("id") String id) {
        boolean success = service.delete(Integer.parseInt(id));
        return new ResponseEntity<>((success) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
