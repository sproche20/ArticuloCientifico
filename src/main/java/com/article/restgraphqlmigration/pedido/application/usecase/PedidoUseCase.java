package com.article.restgraphqlmigration.pedido.application.usecase;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.domain.ports.in.PedidoInPort;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import com.article.restgraphqlmigration.detallePedido.domain.ports.out.DetallePedidoOutPort;
import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

public class PedidoUseCase implements PedidoInPort {

    private final PedidoOutPort pedidoOutPort;
    private final DetallePedidoOutPort detallePedidoOutPort;

    public PedidoUseCase(PedidoOutPort pedidoOutPort, DetallePedidoOutPort detallePedidoOutPort) {
        this.pedidoOutPort = pedidoOutPort;
        this.detallePedidoOutPort = detallePedidoOutPort;
    }

    @Override
    public Pedido createPedido(Pedido pedido) {
        // En un flujo real, aquí podríamos inicializar el pedido con total 0
        return pedidoOutPort.save(pedido);
    }

    @Transactional
    public Pedido calcularTotalPedido(Long pedidoId) {
        List<DetallePedido> detalles = detallePedidoOutPort.findByPedidoId(pedidoId);
        double totalCalculado = detalles.stream()
                .mapToDouble(d -> d.subtotal().doubleValue())
                .sum();

        Pedido pedidoExistente = findPedidoById(pedidoId).orElseThrow();
        Pedido pedidoActualizado = new Pedido(
                pedidoExistente.id(), pedidoExistente.clienteId(),
                pedidoExistente.productosIds(), totalCalculado,
                pedidoExistente.estado(), pedidoExistente.fechaPedido());

        return pedidoOutPort.save(pedidoActualizado);
    }

    @Override
    public Optional<Pedido> findPedidoById(Long id) {
        return pedidoOutPort.findById(id);
    }

    @Override
    public List<Pedido> findAllPedidos() {
        return pedidoOutPort.findAll();
    }

    @Override
    public Pedido updatePedido(Long id, Pedido pedido) {
        return pedidoOutPort.save(pedido);
    }

    @Override
    public boolean deletePedido(Long id) {
        return pedidoOutPort.deleteById(id);
    }
}