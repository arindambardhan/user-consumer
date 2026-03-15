package com.example.consumer.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${users.api.base-url}")
    private String baseUrl;

    @Bean
    public RestClient restClient() {
        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofSeconds(10))
                        .build())
                .setMaxConnPerRoute(2000)
                .setMaxConnTotal(2000)
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(Timeout.ofSeconds(3))
                .setConnectionRequestTimeout(Timeout.ofSeconds(3))
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .evictExpiredConnections()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .evictIdleConnections(Timeout.ofMinutes(3))
                .build();

        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
                .build();
    }
}
