package com.sebastian.controllers;

import com.sebastian.dto.ProductRequest;
import com.sebastian.dto.ProductResponse;
import com.sebastian.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<Void> saveProduct(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok().body(productResponse);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponseList = productService.getAllProducts();
        return ResponseEntity.ok().body(productResponseList);
    }

    @PutMapping("/product/price")
    public ResponseEntity<String> updateProductPrice(@RequestParam String productName, @RequestParam double price) {
        String productResponse = productService.updateProductPrice(productName, price);
        return ResponseEntity.ok().body(productResponse);
    }

    @PutMapping("/product/quantity")
    public ResponseEntity<String> updateProductQuantity(@RequestParam String productName, @RequestParam int quantity) {
        String productResponse = productService.updateProductQuantity(productName, quantity);
        return ResponseEntity.ok().body(productResponse);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
