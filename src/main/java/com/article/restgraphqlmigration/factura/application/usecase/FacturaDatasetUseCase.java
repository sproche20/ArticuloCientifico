package com.article.restgraphqlmigration.factura.application.usecase;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import com.article.restgraphqlmigration.factura.domain.ports.out.FacturaOutPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FacturaDatasetUseCase {
    private static final Logger log = LoggerFactory.getLogger(FacturaDatasetUseCase.class);
    private final FacturaOutPort facturaOutPort;

    public FacturaDatasetUseCase(FacturaOutPort facturaOutPort) {
        this.facturaOutPort = facturaOutPort;
    }

    public void generarFacturasMasivas(int cantidad, int maxPedidoId) {
        int batchSize = 1000;
        List<Factura> facturas = new ArrayList<>(batchSize);

        log.info("Iniciando generación de {} facturas...", cantidad);

        for (int i = 1; i <= cantidad; i++) {
            long pedidoId = ThreadLocalRandom.current().nextLong(1, maxPedidoId + 1);
            double totalAleatorio = ThreadLocalRandom.current().nextDouble(50.0, 2000.0);

            facturas.add(new Factura(
                    null,
                    pedidoId,
                    "FAC-" + System.currentTimeMillis() + "-" + i,
                    totalAleatorio,
                    totalAleatorio,
                    LocalDateTime.now().minusMinutes(ThreadLocalRandom.current().nextLong(0, 10000))));

            if (i % batchSize == 0 || i == cantidad) {
                facturaOutPort.saveAll(facturas);
                log.info("Progreso: {}/{} facturas guardadas...", i, cantidad);
                facturas.clear();
            }
        }
        log.info("¡Carga masiva de facturas finalizada!");
    }
}