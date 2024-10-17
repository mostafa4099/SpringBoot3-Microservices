package org.mostafa.service;

import org.mostafa.model.OrderReqModel;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/11/2024 3:54 PM
 */
public interface OrderService {
    void placeOrder(OrderReqModel orderRequest);
}
