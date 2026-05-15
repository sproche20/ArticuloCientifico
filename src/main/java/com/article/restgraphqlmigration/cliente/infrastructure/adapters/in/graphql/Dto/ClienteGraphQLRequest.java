package com.article.restgraphqlmigration.cliente.infrastructure.adapters.in.graphql.Dto;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import java.time.LocalDateTime;

/**
 * En GraphQL, este record representará el "Input Type" definido en el esquema.
 */
public record ClienteGraphQLRequest(
        String nombres,
        String apellidos,
        String correo,
        String telefono,
        String direccion,
        Boolean activo) {

    public Cliente toDomain() {
        return new Cliente(
                null, nombres, apellidos, correo, telefono, direccion, activo, LocalDateTime.now());
    }
}