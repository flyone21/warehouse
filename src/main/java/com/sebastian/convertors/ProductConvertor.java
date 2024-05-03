package com.sebastian.convertors;

import com.sebastian.dto.ProductRequest;
import com.sebastian.dto.ProductResponse;
import com.sebastian.models.Product;

public class ProductConvertor {


    private ProductConvertor() {
    }


    public static Product convertFrom(ProductRequest request) {
        return new Product(
                request.getName(),
                request.getPrice(),
                request.getQuantity()
        );
    }

    public static ProductResponse convertFrom(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

}
