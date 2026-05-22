package com.article.restgraphqlmigration.producto.infrastructure.adapters.in.graphql.Dto;

import com.article.restgraphqlmigration.producto.domain.model.Producto;

public record ProductoGraphQLRequest(
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Boolean activo) {

    public Producto toDomain() {
        return new Producto(null, nombre, descripcion, precio, stock, activo);
    }
}