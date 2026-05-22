package com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.mapper;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toDomain(ProductoEntity entity) {
        if (entity == null)
            return null;
        return new Producto(
                entity.getId(), entity.getNombre(), entity.getDescripcion(),
                entity.getPrecio(), entity.getStock(), entity.getActivo());
    }

    public ProductoEntity toEntity(Producto domain) {
        if (domain == null)
            return null;
        ProductoEntity entity = new ProductoEntity();
        entity.setId(domain.id());
        entity.setNombre(domain.nombre());
        entity.setDescripcion(domain.descripcion());
        entity.setPrecio(domain.precio());
        entity.setStock(domain.stock());
        entity.setActivo(domain.activo());
        return entity;
    }
}