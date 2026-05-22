package com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.mapper;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.entities.FacturaEntity;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {

    public Factura toDomain(FacturaEntity entity) {
        if (entity == null)
            return null;
        return new Factura(
                entity.getId(),
                entity.getPedido() != null ? entity.getPedido().getId() : null,
                entity.getNumeroFactura(),
                entity.getSubtotal(), entity.getTotal(), entity.getFechaEmision());
    }

    public FacturaEntity toEntity(Factura domain) {
        if (domain == null)
            return null;
        FacturaEntity entity = new FacturaEntity();
        entity.setId(domain.id());

        if (domain.pedidoId() != null) {
            PedidoEntity pedido = new PedidoEntity();
            pedido.setId(domain.pedidoId());
            entity.setPedido(pedido);
        }

        entity.setNumeroFactura(domain.numeroFactura());
        entity.setSubtotal(domain.subtotal());
        entity.setTotal(domain.total());
        entity.setFechaEmision(domain.fechaEmision());
        return entity;
    }
}