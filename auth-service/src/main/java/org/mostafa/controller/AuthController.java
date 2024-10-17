package org.mostafa.controller;

import lombok.RequiredArgsConstructor;
import org.mostafa.model.AuthResponse;
import org.mostafa.service.AuthService;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/10/2024 11:07 AM
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/token")
    public String getToken(@RequestParam String username, @RequestParam String password){
        return authService.singIn(username, password);
    }

    @GetMapping("/validate")
    public AuthResponse validateToken(@RequestParam("token") String token) {
        return authService.validateToken(token);
    }
}
