package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="uid")
    private String uid;

    @Column(name ="name")
    private String name;

    @Column(name ="password")
    private String password;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
