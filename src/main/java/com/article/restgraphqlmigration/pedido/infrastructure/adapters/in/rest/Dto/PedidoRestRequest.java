package com.article.restgraphqlmigration.pedido.infrastructure.adapters.in.rest.Dto;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoRestRequest(
        @NotNull(message = "El ID del cliente es obligatorio") Long clienteId,
        @NotEmpty(message = "Debe incluir al menos un producto") List<Long> productosIds,
        @NotNull(message = "El estado es obligatorio") String estado) {

    public Pedido toDomain() {
        return new Pedido(null, clienteId, productosIds, 0.0, estado, LocalDateTime.now());
    }

    public Pedido toDomainWithId(Long id) {
        return new Pedido(id, clienteId, productosIds, 0.0, estado, LocalDateTime.now());
    }
}