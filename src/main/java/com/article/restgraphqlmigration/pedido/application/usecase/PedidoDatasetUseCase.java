package com.article.restgraphqlmigration.pedido.application.usecase;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PedidoDatasetUseCase {
    private static final Logger log = LoggerFactory.getLogger(PedidoDatasetUseCase.class);
    private final PedidoOutPort pedidoOutPort;

    public PedidoDatasetUseCase(PedidoOutPort pedidoOutPort) {
        this.pedidoOutPort = pedidoOutPort;
    }

    /**
     * Genera pedidos masivos vinculándolos a IDs de clientes existentes.
     * 
     * @param cantidad     Cantidad a generar (ej. 50,000)
     * @param maxClienteId El ID más alto de cliente que tienes en la DB
     */
    public void generarPedidosMasivos(int cantidad, int maxClienteId) {
        int batchSize = 1000;
        List<Pedido> pedidos = new ArrayList<>(batchSize);
        String[] estados = { "PENDIENTE", "PROCESANDO", "COMPLETADO", "CANCELADO" };

        log.info("Iniciando generación de {} pedidos...", cantidad);

        for (int i = 1; i <= cantidad; i++) {
            long clienteId = ThreadLocalRandom.current().nextLong(1, maxClienteId + 1);
            String estado = estados[ThreadLocalRandom.current().nextInt(estados.length)];

            pedidos.add(new Pedido(
                    null,
                    clienteId,
                    Collections.emptyList(),
                    0.0, // Total inicial 0, se calculará al agregar detalles
                    estado,
                    LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextLong(0, 365))));

            if (i % batchSize == 0 || i == cantidad) {
                pedidoOutPort.saveAll(pedidos);
                log.info("Progreso: {}/{} pedidos guardados...", i, cantidad);
                pedidos.clear();
            }
        }
        log.info("¡Carga masiva de pedidos finalizada!");
    }
}