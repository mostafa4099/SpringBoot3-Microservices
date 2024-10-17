package org.mostafa.inventory.service;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/12/2024 1:35 PM
 */
public interface InventoryService {
    boolean isInStock(String skuCode, Integer quantity);
}
