package com.revature.portfolio.models;

import javax.persistence.*;

@Entity
@Table(name = "Developer")
public class Developer {

    @Id
    @Column(name="Username")
    String username;
    String introduction;
    String status;


    private enum Status{
        PUBLIC,
        HIDDEN
    }
}
