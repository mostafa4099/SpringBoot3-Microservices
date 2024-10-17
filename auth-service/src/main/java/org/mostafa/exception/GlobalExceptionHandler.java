package org.mostafa.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.mostafa.model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreatedAt: 9/10/2023
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<AuthResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setExpired(true);
        authResponse.setMessage("Session Expired!");

        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setIllegalArg(true);
        authResponse.setMessage("Illegal Argument.");

        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException ex) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setIllegalArg(true);
        authResponse.setMessage("Jwt Exception. Please Contact with Admin.");

        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setIllegalArg(true);
        authResponse.setMessage("Something went wrong. Please Contact with Admin.");

        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }
}
