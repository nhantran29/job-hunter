package com.job_hunter.service;

import com.job_hunter.domain.User;
import com.job_hunter.dto.UpdateUserRequest;
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
        User user = User.builder().fullName("Nhan").build();
        userRepository.save(user);
        return user;
    }

    public User updateUser(long id, UpdateUserRequest request) {
        Optional<User> optionalUser = get(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFullName(request.getFullName());
            user.setPassword(request.getPassword());
            userRepository.save(user);
            return user;
        }
        return null;
    }

}
