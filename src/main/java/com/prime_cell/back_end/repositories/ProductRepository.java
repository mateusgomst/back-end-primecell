package com.prime_cell.back_end.repositories;

import com.prime_cell.back_end.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Boolean existsByName(String name);
    Product findById(long id);
}
