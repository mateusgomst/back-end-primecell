package com.prime_cell.back_end.controllers;

import com.prime_cell.back_end.exceptions.InvalidFormatEnum;
import com.prime_cell.back_end.models.Product;
import com.prime_cell.back_end.models.ProductType;
import com.prime_cell.back_end.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestBody Product product) {
        // Garante que o ID do produto é o mesmo da URL
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts(){
        List<Product> products= productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/new-products")
    public ResponseEntity<List<Product>> listNewProducts(){
        List<Product> products= productService.getNewProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<Product>> listAllProductsByCategory(@RequestParam("ProductType") String type) {
        List<Product> products = productService.getProductsByCategory(type);
        return ResponseEntity.ok(products);
    }
}
