package com.job_hunter.service;

import com.job_hunter.domain.User;
import com.job_hunter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public Optional<User> get(long id) throws NullPointerException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            throw new NullPointerException("No user found with id " + id);
        }
    }

    public User createUser() {
        User user = new User();
        user.setFullName("Nhan");
        userRepository.save(user);
        return user;
    }

    ;
}
