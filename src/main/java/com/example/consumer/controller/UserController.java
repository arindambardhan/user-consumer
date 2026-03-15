package com.example.consumer.controller;

import com.example.consumer.dto.UserDTO;
import com.example.consumer.service.UserClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/v2")
public class UserController {
    private final UserClientService userClientService;

    @GetMapping
    public CompletableFuture<List<UserDTO>> getUsers() {
        return userClientService.fetchUsers();
    }
}
