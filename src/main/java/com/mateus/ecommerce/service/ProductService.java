package com.mateus.ecommerce.service;

import com.mateus.ecommerce.entity.Product;
import com.mateus.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listarTodos() {
        return repository.findAll();
    }

    public Product salvar(Product product) {
        return repository.save(product);
    }

    public Product buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}