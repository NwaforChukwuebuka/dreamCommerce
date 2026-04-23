package org.dreamCommerce.dreamCommerce.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36, nullable = false)
    private String id;
    private String name;
    private String description;
    @ElementCollection
    private List<String> imageUrls;
    private String price;
}
