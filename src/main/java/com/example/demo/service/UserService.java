package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("UserService")
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User getUserByUid(String uid) {
        return userRepository.findByUid(uid).orElse(null);
    }

    public User deleteUser(String uid) {
        User user = userRepository.findByUid(uid).orElse(null);
        if(user == null) return null;
        user.setDeleted(true);
        return userRepository.save(user); // 사용자 정보 저장
    }

    public User realDeleteUser(String uid) {
        User user = userRepository.findByUid(uid).orElse(null);
        if(user == null) return null;
        userRepository.delete(user);
        return user;
    }
}
