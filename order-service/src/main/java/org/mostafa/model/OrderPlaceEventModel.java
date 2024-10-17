package org.mostafa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/24/2024 4:42 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlaceEventModel {
    private String email;
    private String orderNumber;
}
