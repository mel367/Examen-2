package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(String id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setNombre(productDetails.getNombre());
            product.setPrecio(productDetails.getPrecio());
            product.setExistencia(productDetails.getExistencia());
            product.setDescripcion(productDetails.getDescripcion());
            product.setNombreProv(productDetails.getNombreProv());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}