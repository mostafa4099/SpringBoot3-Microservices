package org.mostafa.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/17/2024 11:55 AM
 */
@RestController
@RequestMapping("/fallback")
public class FallBackController {
    @GetMapping("/product")
    public String productServiceFallBackMessage() {
        return "Product Service Unavailable. Please retry.";
    }

    @GetMapping("/auth")
    public String authServiceFallBackMessage(Throwable throwable) {
        return "Auth Service Unavailable. Please retry.";
    }

    @GetMapping("/common")
    public String requestedServiceFallBackMessage() {
        return "Requested Service Unavailable. Please retry.";
    }
}
