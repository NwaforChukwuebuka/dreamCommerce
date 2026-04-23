package org.dreamCommerce.dreamCommerce.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private List<String> imageUrls;
    private String price;
}
