package com.devanktu.jobconnect.controller;

import com.devanktu.jobconnect.domain.User;
import com.devanktu.jobconnect.service.UserService;
import com.devanktu.jobconnect.util.annotation.ApiMessage;
import com.devanktu.jobconnect.util.exception.IdInvalidException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    @ApiMessage("Create a user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User newUser = this.userService.handleCreateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/users")
    @ApiMessage("Update a user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userUpdated = this.userService.handleUpdateUser(user);
        return ResponseEntity.ok(userUpdated);
    }

    @GetMapping("/users/{id}")
    @ApiMessage("Fetch a user by id")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User fetchUser = this.userService.fetchUserById(id);
        return new ResponseEntity<>(fetchUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    @ApiMessage("Fetch all users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUser());
    }

    @DeleteMapping("/users/{id}")
    @ApiMessage("Delete a user")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws IdInvalidException {
        if (id >= 1500)
            throw new IdInvalidException("Id khong lon hon 1501");
        this.userService.handleDeleteUser(id);
        return new ResponseEntity<>("Deleted user", HttpStatus.OK);
    }
}
