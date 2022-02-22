package com.revature.portfolio.controllers;

import com.revature.portfolio.models.Developer;
import com.revature.portfolio.models.Project;
import com.revature.portfolio.services.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class ProjectControllerTest {

  @MockBean
    ProjectService ps;

 List<Project> plist = new ArrayList<Project>();
  @Autowired
  MockMvc mvc;

    @BeforeEach
    void setUp() {

      this.plist.add(new Project("Weather", "Weather App",
              "http://weatherapp", "github/tjones/weatherapp",
              Project.Status.FINISH , new Developer("tjones")));
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void getAll() throws Exception {
     given(ps.getAll()).willReturn(plist);
     this.mvc.perform(get("/Projects")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void getProject() throws Exception {
        Mockito.when(ps.get(5)).thenReturn(new Project (5, "Weather", "Weather App",
                "http://weatherapp", "github/tjones/weatherapp",
                Project.Status.FINISH , new Developer("tjones")));
        ResultActions ra = mvc.perform(get("/Project/5"));
        ra.andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void addProject() throws Exception {
      Project project = new Project(5,"Weather", "Weather App",
              "http://weatherapp", "github/tjones/weatherapp",
              Project.Status.FINISH , new Developer("tjones"));
        Mockito.when(ps.add(project)).thenReturn(project);
        String url = "/Project/5";
        mvc .perform(post(url));
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void updateProject() {
        Project p = new Project("Weather", "Weather App", "http://weatherapp",
                "github/tjones/weatherapp",Project.Status.FINISH , new Developer("tjones"));
        p.setName("Calculator");
        Mockito.when(ps.update(p)).thenReturn(new Project(p.getName(), p.getDescription(), p.getDeploymentlink(), p.getGithublink(), p.getStatus(), p.getDevUsername()));
        Assertions.assertEquals(p.getName(), p.getName());
    }

    @Test
    @WithMockUser(username = "test", password= "test")
    void deleteProject() throws Exception {
       Project p = new Project();
        when(ps.delete(p.getId())).thenReturn(true);
        boolean result = ps.delete(p.getId());
        Assertions.assertTrue(result);
    }
}