package com.revature.portfolio.models;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User", referencedColumnName = "Username")
    @Column(name="Username")
    String username;
    String password;
    String lastName;
    String firstName;
    String email;
    String phoneNumber;


    private enum Status{
        PUBLIC,
        HIDDEN
    }
}
