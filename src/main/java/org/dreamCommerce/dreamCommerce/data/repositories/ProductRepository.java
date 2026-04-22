package org.dreamCommerce.dreamCommerce.data.repositories;

import org.dreamCommerce.dreamCommerce.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
