package com.revature.portfolio.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @Column(name="username")
    private String username;
    private String introduction;
    private Status status;
    private String role;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private User user;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devUsername")
    private List<Resume> resumeList;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "devUsername")
    private List<Project> projectList;

    public Developer(String username) {
        this.username = username;
    }

    public enum Status{
        PUBLIC,
        HIDDEN
    }
}