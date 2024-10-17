package org.mostafa.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 10/12/2024 11:08 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private boolean isValid;
    private boolean isExpired;
    private boolean isIllegalArg;
    private String message;
    private AuthUser loggedUser;
}
