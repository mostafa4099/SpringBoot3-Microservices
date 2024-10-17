package org.mostafa.repository;

import org.mostafa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/11/2024 3:51 PM
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
