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

    public CompletableFuture<List<UserDTO>> fetchUsers() {
        ExecutorService executor = Executors.newFixedThreadPool(2000);
        try {
            return CompletableFuture.supplyAsync(this::getUserDTOS, executor);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private List<UserDTO> getUserDTOS() {
        return restClient.get()
                .uri("/api/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}