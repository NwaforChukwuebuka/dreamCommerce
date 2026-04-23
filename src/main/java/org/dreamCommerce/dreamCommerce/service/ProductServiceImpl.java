package org.dreamCommerce.dreamCommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dreamCommerce.dreamCommerce.exception.ResourceNotFoundException;
import org.dreamCommerce.dreamCommerce.data.models.Product;
import org.dreamCommerce.dreamCommerce.data.repositories.ProductRepository;
import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.requests.UpdateProductRequest;
import org.dreamCommerce.dreamCommerce.dto.response.AddProductResponse;
import org.dreamCommerce.dreamCommerce.dto.response.UpdateProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        Product product = modelMapper.map(request, Product.class);
        Product savedProduct = productRepository.save(product);
        AddProductResponse response = modelMapper.map(savedProduct, AddProductResponse.class);
        response.setMessage("Product added successfully");
        return response;
    }

    @Override
    public UpdateProductResponse updateProduct(String productId, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Product with id %s not found", productId)
        ));

        if (updateProductRequest.getName() != null) {
            product.setName(updateProductRequest.getName());
        }
        if (updateProductRequest.getDescription() != null) {
            product.setDescription(updateProductRequest.getDescription());
        }
        if (updateProductRequest.getPrice() != null) {
            product.setPrice(updateProductRequest.getPrice());
        }
        if (updateProductRequest.getImages() != null && !updateProductRequest.getImages().isEmpty()) {
            // In a real app, we would upload images to a service (like Cloudinary/S3) and get URLs
            // For now, we'll just mock it by setting the name as a dummy URL
            product.setImageUrls(updateProductRequest.getImages().stream()
                    .map(img -> "http://example.com/" + img.getOriginalFilename())
                    .toList());
        }

        Product updatedProduct = productRepository.save(product);
        UpdateProductResponse response = modelMapper.map(updatedProduct, UpdateProductResponse.class);
        response.setMessage("Product updated successfully");
        return response;
    }
}
