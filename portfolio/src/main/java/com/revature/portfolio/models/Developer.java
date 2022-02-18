package com.revature.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "developer")
@JsonIdentityInfo(property = "username", generator=ObjectIdGenerators.PropertyGenerator.class)
public class Developer {

    @Id
    @Column(name="username")
    private String username;
    private String introduction;
    private Status status;
    private String role;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devUsername")
    private List<Resume> resumeList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devUsername")
    private List<Project> projectList;

    public Developer(String username){
        this.username = username;
    }

    public enum Status{
        PUBLIC,
        HIDDEN
    }

    @Override
    public String toString() {
        return "Developer{" +
                "username='" + username + '\'' +
                ", introduction='" + introduction + '\'' +
                ", status=" + status +
                ", role='" + role + '\'' +
                ", resumeList=" + resumeList +
                ", projectList=" + projectList +
                '}';
    }
}