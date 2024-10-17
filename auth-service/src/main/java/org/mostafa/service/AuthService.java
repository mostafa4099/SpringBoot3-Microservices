package org.mostafa.service;

import org.mostafa.model.AuthResponse;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/10/2024 11:08 AM
 */
public interface AuthService {
    String singIn(String username, String password);

    AuthResponse validateToken(String token);

    void initUsers();
}
