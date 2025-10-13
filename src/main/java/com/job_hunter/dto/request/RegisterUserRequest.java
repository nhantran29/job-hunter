package com.job_hunter.dto.request;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String userName;
    private String password;
    private String email;

}
