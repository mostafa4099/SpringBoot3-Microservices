package org.mostafa.service;

import lombok.RequiredArgsConstructor;
import org.mostafa.config.security.JwtProvider;
import org.mostafa.entity.User;
import org.mostafa.model.AuthResponse;
import org.mostafa.model.AuthUser;
import org.mostafa.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/10/2024 11:09 AM
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public String singIn(String username, String password) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            if (authenticate.isAuthenticated()) {
                return jwtProvider.createToken(user.getUsername());
            } else {
                throw new RuntimeException("Invalid access");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public AuthResponse validateToken(String token) {
        AuthResponse authResponse = new AuthResponse();
        if (jwtProvider.isValidToken(token)) {
            Optional<User> optionalUser = userRepository.findByUsername(jwtProvider.extractUsername(token));
            if (optionalUser.isPresent()) {
                AuthUser authUser = new AuthUser().SetLoggedAuthUser(optionalUser.get());
                authResponse.SetAuthResponse(authUser);
            } else {
                authResponse.setMessage("User not found!");
                authResponse.setIllegalArg(true);
            }
        }
        return authResponse;
    }

    @Override
    public void initUsers() {
        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()) {
            userRepository.saveAll(Stream.of(
                    new User(0L, "mostafa", passwordEncoder.encode("mostafa"), "Md Golam Mostafa"),
                    new User(0L, "manha", passwordEncoder.encode("manha"), "Manha Binte Mostafa")
            ).toList());
        }
    }
}
