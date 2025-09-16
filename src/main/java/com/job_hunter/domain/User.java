package com.job_hunter.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "jh_users")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class User extends JHEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "pass_word")
    private String password;

}
