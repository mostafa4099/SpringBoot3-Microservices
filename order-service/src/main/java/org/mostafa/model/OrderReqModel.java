package org.mostafa.model;

import java.math.BigDecimal;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/11/2024 3:53 PM
 */
public record OrderReqModel(Long id, String orderNumber, String skuCode,
                            BigDecimal price, Integer quantity) {
}
