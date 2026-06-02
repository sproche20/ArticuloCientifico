package com.article.restgraphqlmigration.producto.application.usecase;

import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import com.article.restgraphqlmigration.producto.domain.ports.out.ProductoOutPort;

import java.util.List;
import java.util.ArrayList;

public class ProductoDatasetUseCase {
    private static final Logger log = LoggerFactory.getLogger(ProductoDatasetUseCase.class);
    private final ProductoOutPort productoOutPort;

    public ProductoDatasetUseCase(
            ProductoOutPort productoOutPort) {

        this.productoOutPort = productoOutPort;
    }

    public void generarProductos(int cantidad) {
        int batchSize = 1000; // Procesamos de 1000 en 1000 para proteger la RAM
        List<Producto> productos = new ArrayList<>(batchSize);

        for (int i = 1; i <= cantidad; i++) {
            Producto producto = new Producto(
                    null,
                    "Producto " + i,
                    "Descripcion " + i,
                    ThreadLocalRandom.current().nextDouble(10, 500),
                    ThreadLocalRandom.current().nextInt(1, 100),
                    true);

            productos.add(producto);

            // Cuando llegamos al tamaño del lote, guardamos y limpiamos la lista
            if (i % batchSize == 0 || i == cantidad) {
                productoOutPort.saveAll(productos);
                log.info("Progreso: {}/{} productos guardados...", i, cantidad);
                productos.clear();
            }
        }
    }
}
