package com.prime_cell.back_end.controllers;

import com.prime_cell.back_end.models.Product;
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

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@RequestParam String id) {
        Product product = productService.deleteProduct();
    }*/


    @PostMapping("/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts(){
        List<Product> products= productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

}
