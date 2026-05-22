package com.article.restgraphqlmigration.producto.infrastructure.adapters.in.rest.Dto;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductoRestRequest(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        String descripcion,
        @NotNull(message = "El precio es obligatorio") @Positive(message = "El precio debe ser positivo") Double precio,
        @NotNull(message = "El stock es obligatorio") Integer stock,
        @NotNull(message = "El estado activo es obligatorio") Boolean activo) {

    public Producto toDomain() {
        return new Producto(null, nombre, descripcion, precio, stock, activo);
    }

    public Producto toDomainWithId(Long id) {
        return new Producto(id, nombre, descripcion, precio, stock, activo);
    }
}