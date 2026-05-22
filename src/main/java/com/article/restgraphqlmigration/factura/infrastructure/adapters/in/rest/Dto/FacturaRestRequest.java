package com.article.restgraphqlmigration.factura.infrastructure.adapters.in.rest.Dto;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record FacturaRestRequest(
        @NotNull(message = "El ID del pedido es obligatorio") Long pedidoId,
        @NotBlank(message = "El número de factura es obligatorio") String numeroFactura) {

    public Factura toDomain() {
        return new Factura(null, pedidoId, numeroFactura, 0.0, 0.0, LocalDateTime.now());
    }
}