package com.revature.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor

@Entity
@Table(name = "project")
//@JsonIdentityInfo(property = "id", generator= ObjectIdGenerators.PropertyGenerator.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;

    private String name;
    private String description;
    private String deploymentlink;
    private String imgLink;
    private String githublink;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "username")
    private Developer devUsername;

    private enum Status {
        FINISHED,UNFINISHED
    }

    public Project(String name, String description, String deploymentlink, String imgLink, String githublink, Status status, Developer devUsername) {
        this.name = name;
        this.description = description;
        this.deploymentlink = deploymentlink;
        this.imgLink = imgLink;
        this.githublink = githublink;
        this.status = status;
        this.devUsername = devUsername;
    }

    @Override
    public String toString() {
        return "Project{" +

                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deploymentlink='" + deploymentlink + '\'' +
                ", githublink='" + githublink + '\'' +
                ", status=" + status +
                '}';
    }
}


