package com.article.restgraphqlmigration.pedido.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public record Pedido(
                Long id,
                Long clienteId,
                List<Long> productosIds,
                Double total,
                String estado,
                LocalDateTime fechaPedido) {
}