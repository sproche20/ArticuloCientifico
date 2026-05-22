package com.article.restgraphqlmigration.producto.domain.ports.out;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoOutPort {
    Producto save(Producto producto);

    Optional<Producto> findById(Long id);

    List<Producto> findAll();

    boolean deleteById(Long id);
}