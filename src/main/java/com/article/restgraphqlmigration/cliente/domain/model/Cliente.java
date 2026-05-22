package com.article.restgraphqlmigration.cliente.domain.model;

import java.util.List;
import java.time.LocalDateTime;
import com.article.restgraphqlmigration.pedido.domain.model.Pedido;

public record Cliente(
        Long id,
        String nombres,
        String apellidos,
        String correo,
        String telefono,
        String direccion,
        Boolean activo,
        LocalDateTime fechaRegistro,
        List<Pedido> pedidos) {
}
