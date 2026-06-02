package com.article.restgraphqlmigration.producto.infrastructure.adapters.in.rest.controller;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import com.article.restgraphqlmigration.producto.domain.ports.in.ProductoInPort;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.in.rest.Dto.ProductoRestRequest;
import com.article.restgraphqlmigration.producto.application.usecase.ProductoDatasetUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/productos")
@RequiredArgsConstructor
public class ProductoRestResource {

    private final ProductoInPort productoInPort;
    private final ProductoDatasetUseCase productoDatasetUseCase;

    @GetMapping
    public List<Producto> getAll() {
        return productoInPort.findAllProductos();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoInPort.findProductoById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@Valid @RequestBody ProductoRestRequest request) {
        return productoInPort.createProducto(request.toDomain());
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @Valid @RequestBody ProductoRestRequest request) {
        return productoInPort.updateProducto(id, request.toDomainWithId(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoInPort.deleteProducto(id);
    }

    @PostMapping("/generar")
    public ResponseEntity<String> generar(
            @RequestParam int cantidad) {

        productoDatasetUseCase.generarProductos(cantidad);

        return ResponseEntity.ok(
                cantidad + " productos generados");
    }
}