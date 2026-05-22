package com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.mapper;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.entities.DetallePedidoEntity;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {

    public DetallePedido toDomain(DetallePedidoEntity entity) {
        if (entity == null)
            return null;
        return new DetallePedido(
                entity.getId(),
                entity.getPedido() != null ? entity.getPedido().getId() : null,
                entity.getProducto() != null ? entity.getProducto().getId() : null,
                entity.getCantidad(), entity.getPrecioUnitario(), entity.getSubtotal());
    }

    public DetallePedidoEntity toEntity(DetallePedido domain) {
        if (domain == null)
            return null;
        DetallePedidoEntity entity = new DetallePedidoEntity();
        entity.setId(domain.id());

        if (domain.pedidoId() != null) {
            PedidoEntity pedido = new PedidoEntity();
            pedido.setId(domain.pedidoId());
            entity.setPedido(pedido);
        }

        if (domain.productoId() != null) {
            ProductoEntity producto = new ProductoEntity();
            producto.setId(domain.productoId());
            entity.setProducto(producto);
        }

        entity.setCantidad(domain.cantidad());
        entity.setPrecioUnitario(domain.precioUnitario());
        entity.setSubtotal(domain.subtotal());
        return entity;
    }
}