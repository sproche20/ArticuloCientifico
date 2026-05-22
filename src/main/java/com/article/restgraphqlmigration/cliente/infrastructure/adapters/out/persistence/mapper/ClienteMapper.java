package com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.mapper;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.entities.ClienteEntity;
import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.mapper.PedidoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteMapper {
    private final PedidoMapper pedidoMapper;

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null)
            return null;
        return new Cliente(
                entity.getId(), entity.getNombres(), entity.getApellidos(),
                entity.getCorreo(), entity.getTelefono(), entity.getDireccion(), entity.getActivo(),
                entity.getFechaRegistro(),
                entity.getPedidos() != null
                        ? entity.getPedidos().stream().map(pedidoMapper::toDomain).collect(Collectors.toList())
                        : Collections.emptyList());
    }

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null)
            return null;
        ClienteEntity entity = new ClienteEntity();
        entity.setId(domain.id());
        entity.setNombres(domain.nombres());
        entity.setApellidos(domain.apellidos());
        entity.setCorreo(domain.correo());
        entity.setTelefono(domain.telefono());
        entity.setDireccion(domain.direccion());
        entity.setActivo(domain.activo());
        entity.setFechaRegistro(domain.fechaRegistro());
        return entity;
    }
}