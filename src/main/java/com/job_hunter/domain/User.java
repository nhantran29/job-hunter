package com.job_hunter.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "jh_users")
public class User extends JHEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "pass_word")
    private String password;

}
