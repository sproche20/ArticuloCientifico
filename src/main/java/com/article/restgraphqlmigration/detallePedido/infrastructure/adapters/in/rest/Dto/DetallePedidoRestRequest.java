package com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.in.rest.Dto;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record DetallePedidoRestRequest(
        @NotNull(message = "El ID del pedido es obligatorio") Long pedidoId,
        @NotNull(message = "El ID del producto es obligatorio") Long productoId,
        @NotNull(message = "La cantidad es obligatoria") @Positive(message = "La cantidad debe ser positiva") Integer cantidad,
        @NotNull(message = "El precio unitario es obligatorio") BigDecimal precioUnitario) {

    public DetallePedido toDomain() {
        BigDecimal subtotal = precioUnitario.multiply(new BigDecimal(cantidad));
        return new DetallePedido(null, pedidoId, productoId, cantidad, precioUnitario, subtotal);
    }
}