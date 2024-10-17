package org.mostafa.product.model;

import java.math.BigDecimal;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/10/2024 10:03 PM
 */

public record ProductResponseModel(String id,String name,String description,BigDecimal price){}
