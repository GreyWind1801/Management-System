package com.example.demo.services;

import com.example.demo.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    void resetPassword(Long userId, String newPassword);
}
