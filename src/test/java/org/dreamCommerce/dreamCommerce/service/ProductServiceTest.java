package org.dreamCommerce.dreamCommerce.service;

import org.dreamCommerce.dreamCommerce.data.models.Product;
import org.dreamCommerce.dreamCommerce.data.repositories.ProductRepository;
import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.requests.UpdateProductRequest;
import org.dreamCommerce.dreamCommerce.dto.response.AddProductResponse;
import org.dreamCommerce.dreamCommerce.dto.response.UpdateProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCanAddProduct() {
        AddProductRequest productRequest = new AddProductRequest();
        productRequest.setName("Laptop");
        productRequest.setDescription("A high-end gaming laptop");

        Product productToSave = new Product();
        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Laptop");
        savedProduct.setDescription("A high-end gaming laptop");

        AddProductResponse expectedResponse = new AddProductResponse();
        expectedResponse.setId(1L);

        Mockito.when(modelMapper.map(any(AddProductRequest.class), eq(Product.class))).thenReturn(productToSave);
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        Mockito.when(modelMapper.map(any(Product.class), eq(AddProductResponse.class))).thenReturn(expectedResponse);

        AddProductResponse response = productService.addProduct(productRequest);
        assertThatProductWasAdded(response);
    }

    private static void assertThatProductWasAdded(AddProductResponse response) {
        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    void testCanUpdateProduct(){
        String productId = "1000";
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setName("New Name");

        Product existingProduct = new Product();
        existingProduct.setId(1000L);

        Product updatedProduct = new Product();
        updatedProduct.setId(1000L);
        updatedProduct.setName("New Name");

        UpdateProductResponse expectedResponse = new UpdateProductResponse();
        expectedResponse.setId(1000L);

        Mockito.when(productRepository.findById(1000L)).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        Mockito.when(modelMapper.map(any(Product.class), eq(UpdateProductResponse.class))).thenReturn(expectedResponse);

        UpdateProductResponse updateProductResponse= productService.updateProduct(productId, updateProductRequest);
        assertThat(updateProductResponse).isNotNull();
        assertThat(updateProductResponse.getId()).isEqualTo(Long.valueOf(productId));
    }
}
