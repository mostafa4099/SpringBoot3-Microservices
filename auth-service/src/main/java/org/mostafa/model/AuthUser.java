package org.mostafa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mostafa.entity.User;
import org.springframework.util.CollectionUtils;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreatedAt: 9/10/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUser {
    private Long id;
    private String name;
    private String username;

    public AuthUser SetLoggedAuthUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();

        return this;
    }
}
