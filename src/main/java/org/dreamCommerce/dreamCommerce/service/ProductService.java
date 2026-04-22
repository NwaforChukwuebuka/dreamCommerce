package org.dreamCommerce.dreamCommerce.service;

import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.requests.UpdateProductRequest;
import org.dreamCommerce.dreamCommerce.dto.response.AddProductResponse;
import org.dreamCommerce.dreamCommerce.dto.response.UpdateProductResponse;


public interface ProductService {

    AddProductResponse addProduct(AddProductRequest productRequest);
    UpdateProductResponse updateProduct(String productId, UpdateProductRequest updateProductRequest);
}
