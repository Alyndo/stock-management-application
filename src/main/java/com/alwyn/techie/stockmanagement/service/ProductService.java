package com.alwyn.techie.stockmanagement.service;

import com.alwyn.techie.stockmanagement.exception.ResourceNotFoundException;
import com.alwyn.techie.stockmanagement.model.Product;
import com.alwyn.techie.stockmanagement.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Cacheable(value = "products")
    public List<Product> getAllProducts(){
        logger.info("Fetching all products");
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Optional<Product> getProductById(Long id){
        logger.info("Fetching product by id: {}", id);
        return productRepository.findById(id);
    }

    @CachePut(value = "products", key = "#product.id")
    public Product createProduct(Product product){
        logger.info("Creating new product: {}", product.getName());
        Product savedProduct = productRepository.save(product);
        rabbitMQSender.send("Product created: " + savedProduct.getName());
        return savedProduct;
    }

    @CachePut(value = "products", key = "#id")
    public Product updateProduct(Long id, Product productDetails){
        logger.info("Updating product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

        product.setName(productDetails.getName());
        product.setQuantity(productDetails.getQuantity());
        product.setPrice(productDetails.getPrice());

        Product updatedProduct = productRepository.save(product);
        rabbitMQSender.send("Product updated: " + updatedProduct.getName());
        return updatedProduct;
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id){
        logger.info("Deleting product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

        productRepository.delete(product);
        rabbitMQSender.send("Product deleted: " + product.getName());
    }
}
