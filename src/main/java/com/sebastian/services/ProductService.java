package com.sebastian.services;

import com.sebastian.dto.ProductRequest;
import com.sebastian.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductRequest productRequest);

    ProductResponse getProductById(int id);

    List<ProductResponse> getAllProducts();

    String updateProductPrice(String productName, double newPrice);

    String updateProductQuantity(String productName, int newQuantity);

    void deleteProductById(int id);
}
