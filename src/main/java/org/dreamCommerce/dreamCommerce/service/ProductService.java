package org.dreamCommerce.dreamCommerce.service;

import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.response.AddProductResponse;

public interface ProductService {

    AddProductResponse addProduct(AddProductRequest productRequest);
}
