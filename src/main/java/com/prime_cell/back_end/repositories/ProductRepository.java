package com.prime_cell.back_end.repositories;

import com.prime_cell.back_end.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Boolean existsByName(String name);
    Product findById(long id);

    List<Product> findAllByCreatedAtAfter(LocalDateTime createdAtAfter);
}
