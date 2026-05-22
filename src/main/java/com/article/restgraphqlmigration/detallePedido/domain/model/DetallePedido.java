package com.article.restgraphqlmigration.detallePedido.domain.model;

import java.math.BigDecimal;

public record DetallePedido(
        Long id,
        Long pedidoId,
        Long productoId,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal) {
}