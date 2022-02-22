package com.revature.portfolio.controllers;

import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.Project;
import com.revature.portfolio.models.Resume;
import com.revature.portfolio.services.ResumeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.portfolio.PortfolioApplication.class)
class ResumeControllerTest {
    @MockBean
    ResumeService rs;
    List<Resume> reslist = new ArrayList<Resume>();
    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setUp() {
     this.reslist.add(new Resume("New Resume", "timmy.com","PUBLIC", new Developer("timturner")));
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void getAll() throws Exception {
        given(rs.getAll()).willReturn(reslist);
        this.mvc.perform(get("/resumes")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void getResume() throws Exception {
        when(rs.get(5)).thenReturn(new Resume(5,"New Resume", "timmy.com","PUBLIC", new Developer("timturner")));
        ResultActions ra = mvc.perform(get("/resumes/5"));
        ra.andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void addResume() throws Exception {
        Resume resume = new Resume(5,
                "New Resume", "timmy.com","PUBLIC", new Developer("timturner"));
        when(rs.add(resume)).thenReturn(resume);
        String url = "/resumes/5";
        mvc .perform(post(url));
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void updateResume() {
        Resume resume= new Resume(
                "New Resume", "timmy.com","PUBLIC", new Developer("timturner"));
        resume.setTitle("Python Resume");
        when(rs.update(resume)).thenReturn(new Resume(resume.getTitle(), resume.getLink(), resume.getStatus(), resume.getDevUsername()));
        Resume r = rs.update(resume);
        Assertions.assertEquals(resume.getTitle(), r.getTitle());
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void deleteResume() {
        Resume resume = new Resume();
        resume.setId(5);
        when(rs.delete(resume.getId())).thenReturn(true);
        boolean result = rs.delete(resume.getId());
        Assertions.assertTrue(result);
    }
}