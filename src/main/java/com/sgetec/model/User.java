package com.sgetec.model;

import lombok.Data;

import javax.persistence.*;

@Data // → Lombok
@Entity // → JPA
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}
