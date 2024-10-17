package org.mostafa;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.mostafa.service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class AuthServiceApplication {
    private final AuthService authService;

    @PostConstruct
    void init() {
        authService.initUsers();
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
