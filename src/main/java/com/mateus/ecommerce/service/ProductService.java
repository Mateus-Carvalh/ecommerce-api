package com.mateus.ecommerce.service;

import com.mateus.ecommerce.dto.ProductRequest;
import com.mateus.ecommerce.dto.ProductResponse;
import com.mateus.ecommerce.entity.Product;
import com.mateus.ecommerce.exception.ProductNotFoundException;
import com.mateus.ecommerce.mapper.ProductMapper;
import com.mateus.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(
            ProductRepository repository,
            ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProductResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ProductResponse> buscarPorNome(String nome) {
        return repository.findByNameContainingIgnoreCase(nome)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProductResponse buscarPorId(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return mapper.toResponse(product);
    }

    public ProductResponse salvar(ProductRequest request) {
        Product product = mapper.toEntity(request);
        Product produtoSalvo = repository.save(product);

        return mapper.toResponse(produtoSalvo);
    }

    public ProductResponse atualizar(Long id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        mapper.updateEntity(request, product);

        Product produtoAtualizado = repository.save(product);

        return mapper.toResponse(produtoAtualizado);
    }

    public void excluir(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        repository.delete(product);
    }
}
