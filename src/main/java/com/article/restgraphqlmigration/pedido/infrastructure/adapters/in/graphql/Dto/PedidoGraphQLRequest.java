package com.article.restgraphqlmigration.pedido.infrastructure.adapters.in.graphql.Dto;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoGraphQLRequest(
        Long clienteId,
        List<Long> productosIds,
        String estado) {

    public Pedido toDomain() {
        return new Pedido(null, clienteId, productosIds, 0.0, estado, LocalDateTime.now());
    }
}