package com.job_hunter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @JsonProperty("full_name")
    private String fullName;
    private String password;
}
