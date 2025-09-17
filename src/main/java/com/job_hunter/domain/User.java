package com.job_hunter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "jh_users")
@SuperBuilder
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class User extends JHEntity {

    @Column(name = "full_name")
    @JsonProperty("full_name")
    private String fullName;

    @Column(name = "pass_word")
    private String password;

}
