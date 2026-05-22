package com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.in.graphql.Dto;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import java.math.BigDecimal;

public record DetallePedidoGraphQLRequest(
        Long pedidoId,
        Long productoId,
        Integer cantidad,
        BigDecimal precioUnitario) {

    public DetallePedido toDomain() {
        BigDecimal subtotal = precioUnitario.multiply(new BigDecimal(cantidad));
        return new DetallePedido(null, pedidoId, productoId, cantidad, precioUnitario, subtotal);
    }
}