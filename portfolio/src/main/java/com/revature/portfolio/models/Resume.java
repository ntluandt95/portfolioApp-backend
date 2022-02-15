package com.revature.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;

    private String title;
    private String link;
    private String status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "username")
    private Developer devUsername;

    public Resume(String title, String link, String status, Developer devUsername) {
        this.title = title;
        this.link = link;
        this.status = status;
        this.devUsername = devUsername;
    }
}

