package org.mostafa.product.controller;

import lombok.RequiredArgsConstructor;
import org.mostafa.product.entity.Product;
import org.mostafa.product.model.ProductRequestModel;
import org.mostafa.product.model.ProductResponseModel;
import org.mostafa.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/10/2024 10:04 PM
 */
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequestModel requestModel) {
        return productService.createProduct(requestModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseModel> getAllProducts() {
        return productService.getAllProducts();
    }
}
