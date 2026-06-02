package com.article.restgraphqlmigration.detallePedido.application.usecase;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import com.article.restgraphqlmigration.detallePedido.domain.ports.out.DetallePedidoOutPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DetallePedidoDatasetUseCase {
    private static final Logger log = LoggerFactory.getLogger(DetallePedidoDatasetUseCase.class);
    private final DetallePedidoOutPort detallePedidoOutPort;

    public DetallePedidoDatasetUseCase(DetallePedidoOutPort detallePedidoOutPort) {
        this.detallePedidoOutPort = detallePedidoOutPort;
    }

    public void generarDetallesMasivos(int cantidad, int maxPedidoId, int maxProductoId) {
        int batchSize = 1000;
        List<DetallePedido> detalles = new ArrayList<>(batchSize);

        log.info("Iniciando generación de {} detalles de pedido...", cantidad);

        for (int i = 1; i <= cantidad; i++) {
            long pedidoId = ThreadLocalRandom.current().nextLong(1, maxPedidoId + 1);
            long productoId = ThreadLocalRandom.current().nextLong(1, maxProductoId + 1);

            int cant = ThreadLocalRandom.current().nextInt(1, 10);
            BigDecimal precio = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(5.0, 100.0));
            BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cant));

            detalles.add(new DetallePedido(
                    null,
                    pedidoId,
                    productoId,
                    cant,
                    precio,
                    subtotal));

            if (i % batchSize == 0 || i == cantidad) {
                detallePedidoOutPort.saveAll(detalles);
                log.info("Progreso: {}/{} detalles guardados...", i, cantidad);
                detalles.clear();
            }
        }
        log.info("¡Carga masiva finalizada!");
    }
}