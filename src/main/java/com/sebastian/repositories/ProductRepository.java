package com.sebastian.repositories;

import com.sebastian.models.Product;

import java.util.List;

public interface ProductRepository {

    void saveProduct(Product product);

    Product getProductById(int id);

    Product getProductByName(String name);

    List<Product> getAllProducts();

    int updateProductPrice(String productName, double price);

    int updateProductQuantity(String productName, int quantity);

    int deleteProductById(int id);
}
