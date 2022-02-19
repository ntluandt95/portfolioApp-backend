//package com.revature.portfolio.controllers;
//
//import com.revature.portfolio.models.Developer;
//import com.revature.portfolio.models.Project;
//import com.revature.portfolio.services.ProjectService;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class ProjectControllerTest {
//
//    @MockBean
//    ProjectService ps;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp()
//    {
//        RestAssuredMockMvc.mockMvc(mockMvc);
//    }
//
//
//    @Test
//    void getAll() {
//        Mockito.when(ps.getAll()).thenReturn(
//                List.of(new Project("Weather", "Weather App", "http://weatherapp",
//                        "github/tjones/weatherapp",Project.Status.FINISH , new Developer("tjones")))
//        );
//
//        RestAssuredMockMvc
//                .given()
//                .auth().none()
//                .when()
//                .get("/Projects")
//                .then()
//                .statusCode(200)
//                .body("$.size()", Matchers.equalTo(1))
//                .body("[0].name", Matchers.equalTo("Weather"))
//                .body("[0].description", Matchers.equalTo("Weather App"))
//                .body("[0].deploymentlink", Matchers.equalTo("http://weatherapp"))
//                .body("[0].githublink", Matchers.equalTo("github/tjones/weatherapp"))
//                .body("[0].status", Matchers.equalTo("FINISH"))
//                .body("[0].devUserName", Matchers.equalTo(new Developer("tjones")));
//
//
//    }
//
//    @Test
//    void getProject() throws Exception {
//
//    }
//    @Test
//    void addProject() {
//
//        RestAssuredMockMvc
//                .given()
//                .auth().with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))
//                .headers("Location", )
//                .contentType("application/json")
//                .body("{\"name\": \"Super\", \"description\" :  \"Super App\"," +
//                        " \"deploymentlink\" :  \"super.com\", \"githublink\": \"tim/super.com\" , \"status\"  : \"FINISH\" , \"devUserName\" :  \"timmturn\"  }")
//                .when()
//                .post("/Projects")
//                .then()
//                .statusCode(200)
//                .headers("Location", Matchers.containsString("/Projects"));
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