package com.sebastian.convertors;

import com.sebastian.dto.ProductRequest;
import com.sebastian.dto.ProductResponse;
import com.sebastian.models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductConvertorTest {


    @Test
    @DisplayName("returns a valid Product object")
    void shouldConvertFromProductRequest() {
        //given
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("book");
        productRequest.setPrice(12.0);
        productRequest.setQuantity(3);

        Product expected = new Product();
        expected.setName("book");
        expected.setPrice(12.0);
        expected.setQuantity(3);

        //when
        Product actual = ProductConvertor.convertFrom(productRequest);

        //then
        //only works on object if equals is implemented.!!!
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("returns a valid ProductResponse object")
    void shouldConvertFromProduct() {
        //given
        Product product = new Product();
        product.setId(1);
        product.setName("book");
        product.setPrice(12.01);
        product.setQuantity(3);

        ProductResponse expected = new ProductResponse(1,  "book", 12.01, 3);

        //when
        ProductResponse actual = ProductConvertor.convertFrom(product);

        //then
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getPrice(), actual.getPrice());

    }

}
