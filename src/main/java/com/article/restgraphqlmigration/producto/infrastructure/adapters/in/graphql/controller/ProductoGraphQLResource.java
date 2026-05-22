package com.article.restgraphqlmigration.producto.infrastructure.adapters.in.graphql.controller;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import com.article.restgraphqlmigration.producto.domain.ports.in.ProductoInPort;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.in.graphql.Dto.ProductoGraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductoGraphQLResource {

    private final ProductoInPort productoInPort;

    @QueryMapping
    public List<Producto> listarProductos() {
        return productoInPort.findAllProductos();
    }

    @QueryMapping
    public Producto obtenerProductoPorId(@Argument Long id) {
        return productoInPort.findProductoById(id).orElse(null);
    }

    @MutationMapping
    public Producto crearProducto(@Argument ProductoGraphQLRequest input) {
        return productoInPort.createProducto(input.toDomain());
    }
}