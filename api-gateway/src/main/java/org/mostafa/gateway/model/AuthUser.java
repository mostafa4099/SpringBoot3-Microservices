package org.mostafa.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
