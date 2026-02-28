package com.example.consumer.controller;

import com.example.consumer.dto.UserDTO;
import com.example.consumer.service.UserClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserClientService userClientService;

    public UserController(UserClientService userClientService) {
        this.userClientService = userClientService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userClientService.fetchUsers();
        return ResponseEntity.ok(users);
    }
}
