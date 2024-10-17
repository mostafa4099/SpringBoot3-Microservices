package org.mostafa.model;

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
    private boolean isValid = false;
    private boolean isExpired = false;
    private boolean isIllegalArg = false;
    private String message = "";
    private AuthUser loggedUser = null;

    public void SetAuthResponse(AuthUser authUser) {
        this.isValid = true;
        this.loggedUser = authUser;
    }
}
