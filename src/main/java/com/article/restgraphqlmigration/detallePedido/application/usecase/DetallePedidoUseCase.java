package com.article.restgraphqlmigration.detallePedido.application.usecase;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import com.article.restgraphqlmigration.detallePedido.domain.ports.in.DetallePedidoInPort;
import com.article.restgraphqlmigration.detallePedido.domain.ports.out.DetallePedidoOutPort;
import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public class DetallePedidoUseCase implements DetallePedidoInPort {

    private final DetallePedidoOutPort detallePedidoOutPort;
    private final PedidoOutPort pedidoOutPort;

    public DetallePedidoUseCase(DetallePedidoOutPort detallePedidoOutPort, PedidoOutPort pedidoOutPort) {
        this.detallePedidoOutPort = detallePedidoOutPort;
        this.pedidoOutPort = pedidoOutPort;
    }

    @Override
    @Transactional
    public DetallePedido createDetallePedido(DetallePedido detallePedido) {
        // 1. Guardar el detalle
        DetallePedido savedDetail = detallePedidoOutPort.save(detallePedido);

        // 2. Recalcular el total del Pedido
        List<DetallePedido> todosLosDetalles = detallePedidoOutPort.findByPedidoId(detallePedido.pedidoId());
        double nuevoTotal = todosLosDetalles.stream()
                .mapToDouble(d -> d.subtotal().doubleValue())
                .sum();

        // 3. Actualizar la cabecera del Pedido
        pedidoOutPort.findById(detallePedido.pedidoId()).ifPresent(pedido -> {
            Pedido pedidoActualizado = new Pedido(
                    pedido.id(), pedido.clienteId(), pedido.productosIds(),
                    nuevoTotal, pedido.estado(), pedido.fechaPedido());
            pedidoOutPort.save(pedidoActualizado);
        });

        return savedDetail;
    }

    @Override
    public Optional<DetallePedido> findDetallePedidoById(Long id) {
        return detallePedidoOutPort.findById(id);
    }

    @Override
    public List<DetallePedido> findAllDetallePedidos() {
        return detallePedidoOutPort.findAll();
    }

    @Override
    public boolean deleteDetallePedido(Long id) {
        return detallePedidoOutPort.deleteById(id);
    }
}