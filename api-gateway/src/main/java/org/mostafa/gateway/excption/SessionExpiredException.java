package org.mostafa.gateway.excption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreatedAt: 9/10/2023
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SessionExpiredException extends RuntimeException {
    public SessionExpiredException(String message) {
        super(message);
    }
}
