package com.article.restgraphqlmigration.producto.domain.ports.in;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoInPort {
    Producto createProducto(Producto producto);

    Optional<Producto> findProductoById(Long id);

    List<Producto> findAllProductos();

    Producto updateProducto(Long id, Producto producto);

    boolean deleteProducto(Long id);
}