package com.article.restgraphqlmigration.factura.infrastructure.adapters.in.graphql.Dto;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import java.time.LocalDateTime;

public record FacturaGraphQLRequest(
        Long pedidoId,
        String numeroFactura) {

    public Factura toDomain() {
        return new Factura(null, pedidoId, numeroFactura, 0.0, 0.0, LocalDateTime.now());
    }
}