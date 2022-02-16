package com.revature.portfolio.services;

import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.Project;
import com.revature.portfolio.models.Resume;
import com.revature.portfolio.repositories.ResumeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = com.revature.portfolio.PortfolioApplication.class)
class ResumeServiceImplTest {

    @Autowired
    ResumeService rs;
  @MockBean
    ResumeRepo rr;



    @Test
    void add() {
        Resume resume = new Resume(
                "New Resume", "timmy.com","PUBLIC", new Developer("timturner"));

        Mockito.when(rr.save(resume)).thenReturn(
                new Resume(resume.getTitle(), resume.getLink(), resume.getStatus(), resume.getDevUsername()));

        resume = rs.add(resume);

        Assertions.assertEquals("New Resume", resume.getTitle());
    }

    @Test
    void get() {
        Resume resume = new Resume(
                "New Resume", "timmy.com","PUBLIC", new Developer("timturner"));
        Optional<Resume> po = Optional.of(resume);
        Mockito.when(rr.findById(resume.getId())).thenReturn(po);
        Resume actual = rs.get(resume.getId());
        Assertions.assertEquals(actual.getId(), resume.getId());

    }

    @Test
    void getAll() {
        List<Resume> list = new ArrayList<Resume>();
        list.add(new Resume());
        given(rr.findAll()).willReturn(list);
        List<Resume> expected = rs.getAll();
        Assertions.assertEquals(expected, list);


    }

    @Test
    void getbyDeveloper() {
        Developer dev = new Developer();
        dev.setUsername("timturner");
        List<Resume>p = new ArrayList<Resume>();
        p.add(new Resume());

        given(rr.findByDevUsername(dev.getUsername())).willReturn(p);
        List<Resume> expected = rs.getbyDeveloper(dev.getUsername());
        Assertions.assertEquals(expected, p);

    }

    @Test
    void update() {
        Resume resume= new Resume(
                "New Resume", "timmy.com","PUBLIC", new Developer("timturner"));
        resume.setTitle("Python Resume");
        Mockito.when(rr.save(resume)).thenReturn(new Resume(resume.getTitle(), resume.getLink(), resume.getStatus(), resume.getDevUsername()));
        Resume r = rs.update(resume);
        Assertions.assertEquals(resume.getTitle(), r.getTitle());
    }

    @Test
    void delete() {
        Resume resume= new Resume(
                "New Resume", "timmy.com","PUBLIC", new Developer("timturner"));
        Mockito.doNothing().when(rr).deleteById(resume.getId());
        boolean result = rs.delete(resume.getId());
        Assertions.assertTrue(result);
    }
}