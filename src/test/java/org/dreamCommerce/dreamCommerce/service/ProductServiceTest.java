package org.dreamCommerce.dreamCommerce.service;

import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.response.AddProductResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductServiceTest {

    @Test
    void testCanAddProduct() {
        AddProductRequest productRequest = new AddProductRequest();
        add
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductServiceImpl(productRepository);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(new Product());
        AddProductResponse response = productService.addProduct(productRequest);
        assertNotNull(response);
        assertNotNull(response.getId().isNotNull);


    }
}
