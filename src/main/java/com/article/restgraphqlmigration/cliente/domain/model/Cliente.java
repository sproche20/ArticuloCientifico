package com.article.restgraphqlmigration.cliente.domain.model;

import java.time.LocalDateTime;

public record Cliente(
                Long id,
                String nombres,
                String apellidos,
                String correo,
                String telefono,
                String direccion,
                Boolean activo,
                LocalDateTime fechaRegistro) {
}
