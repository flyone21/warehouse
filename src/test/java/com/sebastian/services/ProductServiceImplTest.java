package com.sebastian.services;

import com.sebastian.dto.ProductResponse;
import com.sebastian.exceptions.ProductNotFoundException;
import com.sebastian.models.Product;
import com.sebastian.repositories.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl underTest;

    @Test
    @DisplayName("returns an product if the object is found in database")
    void shouldReturnProduct() {
        //given
        int productId = 1;
        Product product = new Product(1, "book", 2.23, 10);
        ProductResponse expected = new ProductResponse(1, "book", 2.23, 10);

        given(productRepository.getProductById(1)).willReturn(product);


        //when
        ProductResponse actual = underTest.getProductById(productId);

        //then
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getPrice(), actual.getPrice());

    }

    @Test
    @DisplayName("throws ProductNotFoundException if the product is not found in the database")
    void shouldThrowError() {
        //given
        int productId = 1;

        given(productRepository.getProductById(1)).willReturn(null);

        //when

        Exception actual = assertThrows(ProductNotFoundException.class, () -> underTest.getProductById(productId));

        //then
        assertEquals(ProductNotFoundException.class, actual.getClass());
        assertEquals("Product not found", actual.getMessage());
    }

}
