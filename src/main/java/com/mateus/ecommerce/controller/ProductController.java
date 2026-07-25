package com.mateus.ecommerce.controller;

import com.mateus.ecommerce.dto.ProductRequest;
import com.mateus.ecommerce.dto.ProductResponse;
import com.mateus.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> buscarPorId(
            @PathVariable Long id) {

        ProductResponse product = service.buscarPorId(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }
    @PostMapping
    public ResponseEntity<ProductResponse> salvar(
        @Valid @RequestBody ProductRequest request) {

        ProductResponse produtoSalvo = service.salvar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoSalvo);
    }

   @PutMapping("/{id}")
public ResponseEntity<ProductResponse> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody ProductRequest request) {

        ProductResponse produtoAtualizado =
                service.atualizar(id, request);

        if (produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = service.excluir(id);

        if (!excluido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}