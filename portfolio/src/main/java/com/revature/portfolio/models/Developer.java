package com.revature.portfolio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private User user;


    public enum Status{
        PUBLIC,
        HIDDEN
    }
}
