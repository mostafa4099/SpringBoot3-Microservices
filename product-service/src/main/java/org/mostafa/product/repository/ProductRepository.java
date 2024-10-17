package org.mostafa.product.repository;

import org.mostafa.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/10/2024 9:50 PM
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
