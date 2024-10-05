package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create") // Create
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (Objects.equals(user.getUid(), "") ||
                Objects.equals(user.getPassword(), "") ||
                Objects.equals(user.getName(), "")) {
            return new ResponseEntity<>("message: 가입에 필요한 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/read/{uid}") // Read
    public ResponseEntity<?> readUser(@PathVariable String uid) {
        User findUser = userService.getUserByUid(uid);
        if (findUser == null) {
            return new ResponseEntity<>("message: 일치하는 유저가 없습니다.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findUser.getName(), HttpStatus.OK);
    }

    @PostMapping("/delete") // soft하게 삭제함 플래그만 사용 Update
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto) {
        if (userService.deleteUser(userDto.getUid()) == null) {
            return new ResponseEntity<>("message: 일치하는 유저가 없습니다.", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>("message: 성공적으로 삭제되었습니다.", HttpStatus.OK);
        }
    }

    @PostMapping("/delete/real") // 행을 삭제함 Delete
    public ResponseEntity<?> deleteUserReal(@RequestBody UserDto userDto) {
        if (userService.realDeleteUser(userDto.getUid()) == null) {
            return new ResponseEntity<>("message: 일치하는 유저가 없습니다.", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>("message: 성공적으로 삭제되었습니다.", HttpStatus.OK);
        }
    }
}
