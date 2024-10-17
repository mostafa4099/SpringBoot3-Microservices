package org.mostafa.excption;

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
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(InventoryServiceUnavailableException.class)
    public ResponseEntity<String> handleInventoryServiceUnavailableException(InventoryServiceUnavailableException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }
}
