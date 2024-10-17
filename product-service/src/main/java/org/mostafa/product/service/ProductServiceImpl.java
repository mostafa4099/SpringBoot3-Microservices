package org.mostafa.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mostafa.product.entity.Product;
import org.mostafa.product.model.ProductRequestModel;
import org.mostafa.product.model.ProductResponseModel;
import org.mostafa.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/10/2024 10:04 PM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequestModel requestModel) {
        Product product = Product.builder()
                .name(requestModel.name())
                .description(requestModel.description())
                .price(requestModel.price())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return product;
    }

    @Override
    public List<ProductResponseModel> getAllProducts() {
        return productRepository.findAll()
                .stream().map(product -> new ProductResponseModel(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}
