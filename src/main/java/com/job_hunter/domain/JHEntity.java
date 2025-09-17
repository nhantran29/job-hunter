package com.job_hunter.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // hoặc TABLE_PER_CLASS
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@Data
public class JHEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Builder.Default
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Builder.Default
    @Column(name = "last_updated")
    private Date lastUpdated = new Date();
}
