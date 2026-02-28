package com.example.consumer.service;

import com.example.consumer.dto.UserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserClientService {

    private static final ParameterizedTypeReference<List<UserDTO>> USER_LIST_TYPE =
            new ParameterizedTypeReference<>() {};

    private final RestClient restClient;

    public UserClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * Calls the upstream /api/users endpoint.
     * Because virtual threads are enabled (spring.threads.virtual.enabled=true),
     * this blocking RestClient call runs on a virtual thread, freeing platform
     * threads while waiting for the I/O response.
     */
    public List<UserDTO> fetchUsers() {
        return restClient.get()
                .uri("/api/users")
                .retrieve()
                .body(USER_LIST_TYPE);
    }
}
