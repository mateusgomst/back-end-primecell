package com.prime_cell.back_end.services;

import com.prime_cell.back_end.exceptions.InsufficientDataException;
import com.prime_cell.back_end.models.Product;
import com.prime_cell.back_end.repositories.ProductRepository;
import com.prime_cell.back_end.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(long id){
        if(!productRepository.existsById(id)){
            throw new InsufficientDataException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        // Verifica se o produto existe
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new InsufficientDataException("Product with id " + product.getId() + " not found"));

        existingProduct.setName(product.getName());
        return productRepository.save(existingProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        if(!productRepository.existsByName(name)){
            throw new InsufficientDataException("Product with name " + name + " not found");
        }
        return productRepository.findByName(name);
    }

    public Product getProductById(long id) {
        if (!productRepository.existsById(id)) {
            throw new InsufficientDataException("Product with id " + id + " not found");
        }
        return productRepository.findById(id);
    }
}
