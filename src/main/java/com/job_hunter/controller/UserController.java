package com.job_hunter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class UserController {
//    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDb() throws SQLException {
        return dataSource.getConnection().getMetaData().getURL();
    }
    @GetMapping("/")
    public String users() {
        return "userss";
    }
}
