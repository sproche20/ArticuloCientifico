package com.article.restgraphqlmigration.cliente.infrastructure.adapters.in.rest.Dto;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ClienteRestRequest(
        @NotBlank(message = "Los nombres son obligatorios") String nombres,
        @NotBlank(message = "Los apellidos son obligatorios") String apellidos,
        @NotBlank(message = "El correo es obligatorio") @Email(message = "Formato de correo inválido") String correo,
        String telefono,
        String direccion,
        @NotNull(message = "El estado activo es obligatorio") Boolean activo) {

    public Cliente toDomain() {
        return new Cliente(
                null,
                nombres,
                apellidos,
                correo,
                telefono,
                direccion,
                activo,
                LocalDateTime.now());
    }

    public Cliente toDomainWithId(Long id) {
        return new Cliente(
                id,
                nombres,
                apellidos,
                correo,
                telefono,
                direccion,
                activo,
                null // La fecha usualmente no se actualiza por el cliente
        );
    }
}
