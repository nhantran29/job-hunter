package com.job_hunter.controller;

import com.job_hunter.domain.User;
import com.job_hunter.dto.UpdateUserRequest;
import com.job_hunter.repository.UserRepository;
import com.job_hunter.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok().body(userService.updateUser(id, request));
    }
}
