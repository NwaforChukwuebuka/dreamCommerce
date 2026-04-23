package org.dreamCommerce.dreamCommerce.controller;

import lombok.RequiredArgsConstructor;
import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.requests.UpdateProductRequest;
import org.dreamCommerce.dreamCommerce.dto.response.AddProductResponse;
import org.dreamCommerce.dreamCommerce.dto.response.UpdateProductResponse;
import org.dreamCommerce.dreamCommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<AddProductResponse> addProduct(@RequestBody AddProductRequest request) {
        AddProductResponse response = productService.addProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable String productId, @ModelAttribute UpdateProductRequest request) {
        UpdateProductResponse response = productService.updateProduct(productId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
