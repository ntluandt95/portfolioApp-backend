package com.revature.portfolio.models;

import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;

    private String name;
    private String description;
    private String deploymentlink;
    private String githublink;
    private Status status;
    private String devUsername;
}

enum Status {
    FINISH,UNFINISH
}
