package com.revature.portfolio.models;

import com.revature.portfolio.PortfolioApplication;
import com.revature.portfolio.utility.HashGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // cannot have a table named user because it is a keyword in postgres
public class User {

    @Id
    @Column(name="username")
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Status status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Developer developer;


    private enum Status{
        PUBLIC,
        HIDDEN
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = PortfolioApplication.app.getBean(BCryptPasswordEncoder.class);
        this.password = encoder.encode(password);
    }




}