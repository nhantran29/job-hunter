package com.job_hunter.controller;

import com.job_hunter.domain.User;
import com.job_hunter.repository.UserRepository;
import com.job_hunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController extends RestfulController {

    public final UserRepository userRepository;
    public final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok().body(userService.list());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable long id) throws NullPointerException {
        return ResponseEntity.ok().body(userService.get(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser() {
        return ResponseEntity.ok().body(userService.createUser());
    }
}
