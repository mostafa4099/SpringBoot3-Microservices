package org.mostafa.product.service;

import org.mostafa.product.entity.Product;
import org.mostafa.product.model.ProductRequestModel;
import org.mostafa.product.model.ProductResponseModel;

import java.util.List;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/10/2024 10:04 PM
 */
public interface ProductService {
    Product createProduct(ProductRequestModel requestModel);

    List<ProductResponseModel> getAllProducts();
}
