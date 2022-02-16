package com.revature.portfolio.services;

import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.Project;
import com.revature.portfolio.repositories.ProjectRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = com.revature.portfolio.PortfolioApplication.class)
public class ProjectServiceImplTest {

    @MockBean
ProjectRepo pr;
    @Autowired
 ProjectService ps;


    @Test
    void add() {
        Project project = new Project("Weather", "Weather App",
                "http://weatherapp", "github/tjones/weatherapp",Project.Status.FINISH , new Developer("tjones"));
        Mockito.when(pr.save(project)).thenReturn(
                new Project(project.getName(), project.getDescription(), project.getDeploymentlink(), project.getGithublink(), project.getStatus(), project.getDevUsername()));

            project = ps.add(project);

            Assertions.assertEquals("Weather", project.getName());
    }

    @Test
    void get() {
        Project project = new Project("Weather", "Weather App", "http://weatherapp",
                "github/tjones/weatherapp",Project.Status.FINISH , new Developer("tjones"));
        Optional<Project> po = Optional.of(project);
        Mockito.when(pr.findById(project.getId())).thenReturn(po);
        Project actual = ps.get(project.getId());
        Assertions.assertEquals(actual.getId(), project.getId());
    }

    @Test
    void getAll() {
        List<Project> list = new ArrayList<Project>();
        list.add(new Project());
        given(pr.findAll()).willReturn(list);
        List<Project> expected = ps.getAll();
        Assertions.assertEquals(expected, list);

    }

    @Test
    void getbyDeveloper() {
        Developer dev = new Developer();
        dev.setUsername("tjones");
    List<Project>p = new ArrayList<Project>();
    p.add(new Project());

    given(pr.findByDevUsername(dev.getUsername())).willReturn(p);
    List<Project> expected = ps.getbyDeveloper(dev.getUsername());
    Assertions.assertEquals(expected, p);

    }


    @Test
    void update() {

        Project p = new Project("Weather", "Weather App", "http://weatherapp",
                "github/tjones/weatherapp",Project.Status.FINISH , new Developer("tjones"));
          p.setName("Calculator");
          Mockito.when(pr.save(p)).thenReturn(new Project(p.getName(), p.getDescription(), p.getDeploymentlink(), p.getGithublink(), p.getStatus(), p.getDevUsername()));
        Project project = ps.update(p);
        Assertions.assertEquals(p.getName(), project.getName());
    }

    @Test
    void delete() {
        Project project = new Project("Weather", "Weather App", "http://weatherapp",
                "github/tjones/weatherapp",Project.Status.FINISH , new Developer("tjones"));
        Mockito.doNothing().when(pr).deleteById(project.getId());
        boolean result = ps.delete(project.getId());
        Assertions.assertTrue(result);

    }
}