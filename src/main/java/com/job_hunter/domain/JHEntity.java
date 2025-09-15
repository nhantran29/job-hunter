package com.job_hunter.domain;

import jakarta.persistence.*;

import java.util.Date;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // hoặc TABLE_PER_CLASS
@MappedSuperclass
public class JHEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_updated")
    private Date lastUpdated;
}
