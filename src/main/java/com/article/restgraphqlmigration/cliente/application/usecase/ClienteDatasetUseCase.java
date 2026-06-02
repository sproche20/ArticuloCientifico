package com.article.restgraphqlmigration.cliente.application.usecase;

import java.time.LocalDateTime;
import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import com.article.restgraphqlmigration.cliente.domain.ports.out.ClienteOutPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;

public class ClienteDatasetUseCase {
    private static final Logger log = LoggerFactory.getLogger(ClienteDatasetUseCase.class);
    private final ClienteOutPort clienteOutPort;

    public ClienteDatasetUseCase(
            ClienteOutPort clienteOutPort) {

        this.clienteOutPort = clienteOutPort;
    }

    public void generarClientes(int cantidad) {
        int batchSize = 1000; // Procesamos en bloques para proteger la RAM
        List<Cliente> clientes = new ArrayList<>(batchSize);

        log.info("Iniciando generación de {} clientes...", cantidad);

        for (int i = 1; i <= cantidad; i++) {
            Cliente cliente = new Cliente(
                    null,
                    "Cliente " + i,
                    "Apellido " + i,
                    "cliente" + i + "@mail.com",
                    "555-000" + i,
                    "Direccion " + i,
                    true,
                    LocalDateTime.now(),
                    List.of());

            clientes.add(cliente);

            // Cuando llegamos al tamaño del lote, guardamos y liberamos memoria
            if (i % batchSize == 0 || i == cantidad) {
                clienteOutPort.saveAll(clientes);
                log.info("Progreso: {}/{} clientes guardados...", i, cantidad);
                clientes.clear();
            }
        }
        log.info("¡Carga masiva de clientes finalizada!");
    }
}
