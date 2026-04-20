package org.dreamCommerce.dreamCommerce.service;

import lombok.RequiredArgsConstructor;
import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.response.AddProductResponse;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productRequest, Product.class);
        product
    }

}
