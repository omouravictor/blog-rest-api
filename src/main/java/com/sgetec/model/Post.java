package com.sgetec.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data // → Lombok
@Entity // → JPA
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date publicationDate;

    @Column(nullable = false)
    private String author;

}