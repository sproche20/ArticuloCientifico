package com.article.restgraphqlmigration.factura.application.usecase;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import com.article.restgraphqlmigration.factura.domain.ports.in.FacturaInPort;
import com.article.restgraphqlmigration.factura.domain.ports.out.FacturaOutPort;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public class FacturaUseCase implements FacturaInPort {

    private final FacturaOutPort facturaOutPort;
    private final PedidoOutPort pedidoOutPort;

    public FacturaUseCase(FacturaOutPort facturaOutPort, PedidoOutPort pedidoOutPort) {
        this.facturaOutPort = facturaOutPort;
        this.pedidoOutPort = pedidoOutPort;
    }

    @Override
    @Transactional
    public Factura createFactura(Factura factura) {
        // Lógica de Orquestación: Obtener el total del pedido para asegurar la factura
        Pedido pedido = pedidoOutPort.findById(factura.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (pedido.total() == null || pedido.total() <= 0) {
            throw new RuntimeException("No se puede facturar un pedido sin productos o con total cero");
        }

        Factura facturaFinal = new Factura(
                null, factura.pedidoId(), factura.numeroFactura(),
                pedido.total(), pedido.total(), factura.fechaEmision());

        return facturaOutPort.save(facturaFinal);
    }

    @Override
    public Optional<Factura> findFacturaById(Long id) {
        return facturaOutPort.findById(id);
    }

    @Override
    public List<Factura> findAllFacturas() {
        return facturaOutPort.findAll();
    }

    @Override
    public boolean deleteFactura(Long id) {
        return facturaOutPort.deleteById(id);
    }
}