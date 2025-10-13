package com.job_hunter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jh_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles= new HashSet<>();

}
