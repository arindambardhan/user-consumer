package com.example.consumer.service;

import com.example.consumer.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserClientService {
    private final RestClient restClient;

    public List<UserDTO> fetchUsers() {
        log.info("Current thread: {}", Thread.currentThread().getName());
        return restClient.get()
                .uri("/api/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}