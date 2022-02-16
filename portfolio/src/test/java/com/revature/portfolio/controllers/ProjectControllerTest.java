//package com.revature.portfolio.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.revature.portfolio.models.Developer;
//import com.revature.portfolio.models.Project;
//import com.revature.portfolio.services.ProjectService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@RunWith(MockitoJUnitRunner.class)
//class ProjectControllerTest {
//
//
//    MockMvc mvc;
//    ObjectMapper objectnew = new ObjectMapper();
//    ObjectWriter objectWriter =  objectnew.writer();
//
//    @Mock
//    ProjectService ps;
//
//    @InjectMocks
//    ProjectController pc;
//
//    @Test
//    void getAll() {
//    }
//
//    @Test
//    void getProject() throws Exception {
//
//      this.mvc.perform(get("/Projects")).andDo(print()).andExpect(status().isOk())
//              .andExpect(content().int
//    }

//    @Test
//    void addProject() {
//    }
//
//    @Test
//    void updateProject() {
//    }
//
//    @Test
//    void deleteProject() {
//    }
//}