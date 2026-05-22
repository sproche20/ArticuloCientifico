package com.article.restgraphqlmigration.factura.domain.model;

import java.time.LocalDateTime;

public record Factura(
        Long id,
        Long pedidoId,
        String numeroFactura,
        Double subtotal,
        Double total,
        LocalDateTime fechaEmision) {
}