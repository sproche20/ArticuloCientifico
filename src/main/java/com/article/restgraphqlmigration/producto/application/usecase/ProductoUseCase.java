package com.article.restgraphqlmigration.producto.application.usecase;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import com.article.restgraphqlmigration.producto.domain.ports.in.ProductoInPort;
import com.article.restgraphqlmigration.producto.domain.ports.out.ProductoOutPort;
import java.util.List;
import java.util.Optional;

public class ProductoUseCase implements ProductoInPort {

    private final ProductoOutPort productoOutPort;

    public ProductoUseCase(ProductoOutPort productoOutPort) {
        this.productoOutPort = productoOutPort;
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoOutPort.save(producto);
    }

    @Override
    public Optional<Producto> findProductoById(Long id) {
        return productoOutPort.findById(id);
    }

    @Override
    public List<Producto> findAllProductos() {
        return productoOutPort.findAll();
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        return productoOutPort.save(producto);
    }

    @Override
    public boolean deleteProducto(Long id) {
        return productoOutPort.deleteById(id);
    }
}