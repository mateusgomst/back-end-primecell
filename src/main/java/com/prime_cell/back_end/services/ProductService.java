package com.prime_cell.back_end.services;

import com.prime_cell.back_end.exceptions.InsufficientDataException;
import com.prime_cell.back_end.models.Product;
import com.prime_cell.back_end.models.ProductType;
import com.prime_cell.back_end.repositories.ProductRepository;
import com.prime_cell.back_end.util.UploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            try {
                // Salva a imagem e obtém o nome do arquivo
                String imageName = UploadUtil.uploadImage(image);
                product.setImage(imageName);
            } catch (RuntimeException e) {
                throw new InsufficientDataException("Erro ao salvar imagem do produto: " + e.getMessage());
            }
        }
        return productRepository.save(product);
    }
    /*public Product addProduct(Product product) {
        return productRepository.save(product);
    }*/

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

    public List<Product> getNewProducts() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);

        List<Product> newProducts = productRepository.findAllByCreatedAtAfter(thirtyDaysAgo);

        if (newProducts.isEmpty()) {
            throw new InsufficientDataException("No new products found");
        }
        return newProducts;
    }

    public List<Product> getProductsByCategory(String type) {
        List<Product> products = productRepository.findByType(ProductType.fromString(type));
        if (products == null || products.isEmpty()) {
            throw new InsufficientDataException("No products found for category " + type);
        }
        return products;
    }
}
