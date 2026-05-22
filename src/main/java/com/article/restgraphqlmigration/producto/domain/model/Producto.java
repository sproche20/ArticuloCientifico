package com.article.restgraphqlmigration.producto.domain.model;

public record Producto(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Boolean activo) {
}