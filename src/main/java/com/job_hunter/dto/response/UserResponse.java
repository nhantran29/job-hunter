package com.job_hunter.dto.response;

import com.job_hunter.domain.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String userName;
    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.userName = user.getUserName();
        this.email = user.getEmail();

    }
}
