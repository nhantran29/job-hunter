package com.job_hunter.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jh_roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
