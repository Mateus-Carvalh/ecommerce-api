package com.mateus.ecommerce.controller;

import com.mateus.ecommerce.dto.ProductRequest;
import com.mateus.ecommerce.dto.ProductResponse;
import com.mateus.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> buscarPorNome(
            @RequestParam String name) {

        List<ProductResponse> produtos = service.buscarPorNome(name);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> buscarPorId(
            @PathVariable Long id) {

        ProductResponse product = service.buscarPorId(id);

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

        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
