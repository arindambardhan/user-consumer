package com.example.consumer.service;

import com.example.consumer.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserClientService {
    private final WebClient webClient;

    public Flux<UserDTO> fetchUsers() {
        return webClient.get()
                .uri("/api/users")
                .retrieve()
                .bodyToFlux(UserDTO.class);
    }
}