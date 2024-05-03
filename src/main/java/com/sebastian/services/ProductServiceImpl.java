package com.sebastian.services;

import com.sebastian.convertors.ProductConvertor;
import com.sebastian.dto.ProductRequest;
import com.sebastian.dto.ProductResponse;
import com.sebastian.exceptions.DeleteException;
import com.sebastian.exceptions.ProductAlreadyExistsException;
import com.sebastian.exceptions.ProductNotFoundException;
import com.sebastian.exceptions.UpdateException;
import com.sebastian.models.Product;
import com.sebastian.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(ProductRequest productRequest) {

        Product findProduct = productRepository.getProductByName(productRequest.getName());

        if (findProduct != null) {
            log.info("A product with name: {} was found", productRequest.getName());
            throw new ProductAlreadyExistsException("Product already exists. Pls update the product values");
        }

        Product product = ProductConvertor.convertFrom(productRequest);
        log.info("Saving product to database");
        productRepository.saveProduct(product);
    }

    @Override
    public ProductResponse getProductById(int id) {
        Product product = productRepository.getProductById(id);

        if(product == null){
            throw new ProductNotFoundException("Product not found");
        }

        log.info("A product with name: {} was found", product.getName());

        ProductResponse productResponse = ProductConvertor.convertFrom(product);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> responses = new ArrayList<>();

        List<Product> products = productRepository.getAllProducts();

        if(products.isEmpty()){
            log.info("No products found");
            return responses;
        }

        for (Product product : products) {
            ProductResponse productResponse = ProductConvertor.convertFrom(product);
            responses.add(productResponse);
        }

        return responses;

    }

    @Override
    public String updateProductPrice(String productName, double newPrice) {
        Product product = productRepository.getProductByName(productName);

        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        int updateResponse = productRepository.updateProductPrice(productName, newPrice);

        if (updateResponse == 0) {
            throw new UpdateException("Product was not updated");
        }

        log.info("A product with name: {} was found and price updated to: {}", product.getName(), newPrice);
        return "Product price for: " + productName + " was updated to: " + newPrice;
    }

    @Override
    public String updateProductQuantity(String productName, int newQuantity) {
        Product product = productRepository.getProductByName(productName);

        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        int updateResponse = productRepository.updateProductQuantity(productName, newQuantity);

        if (updateResponse == 0) {
            throw new UpdateException("Product was not updated");
        }

        int updatedQuantity = product.getQuantity() + newQuantity;

        log.info("A product with name: {} was found and quantity updated: {}", product.getName(), updatedQuantity);
        return "Product quantity for product: -> " + productName + " was updated to: " + updatedQuantity;
    }

    @Override
    public void deleteProductById(int id) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        int deleteResponse = productRepository.deleteProductById(id);

        if (deleteResponse == 0) {
            log.info("A product with name: {} was not deleted", product.getName());
            throw new DeleteException("Product was not deleted");
        }

        log.info("A product with name: {} was found and deleted", product.getName());

    }


}
