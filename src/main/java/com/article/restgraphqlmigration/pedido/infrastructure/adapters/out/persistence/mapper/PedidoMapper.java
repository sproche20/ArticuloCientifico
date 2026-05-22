package com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.mapper;

import com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.entities.ClienteEntity;
import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.entities.ProductoEntity;

import java.util.stream.Collectors;
import java.util.Collections;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public Pedido toDomain(PedidoEntity entity) {
        if (entity == null)
            return null;
        return new Pedido(
                entity.getId(),
                entity.getCliente() != null ? entity.getCliente().getId() : null,
                entity.getDetalles() != null && !entity.getDetalles().isEmpty()
                        ? entity.getDetalles().stream()
                                .filter(d -> d.getProducto() != null) // Asegura que el producto no sea nulo antes de
                                                                      // acceder a su ID
                                .map(d -> d.getProducto().getId())
                                .collect(Collectors.toList())
                        : Collections.emptyList(),
                entity.getTotal(), entity.getEstado(), entity.getFechaPedido());
    }

    public PedidoEntity toEntity(Pedido domain) {
        if (domain == null)
            return null;
        PedidoEntity entity = new PedidoEntity();
        entity.setId(domain.id());

        if (domain.clienteId() != null) {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setId(domain.clienteId());
            entity.setCliente(cliente);
        }

        entity.setTotal(domain.total());
        entity.setEstado(domain.estado());
        entity.setFechaPedido(domain.fechaPedido());
        return entity;
    }
}