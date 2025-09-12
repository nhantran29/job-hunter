package com.job_hunter.domain;

import jakarta.persistence.*;

@Entity
public class JHEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
}
