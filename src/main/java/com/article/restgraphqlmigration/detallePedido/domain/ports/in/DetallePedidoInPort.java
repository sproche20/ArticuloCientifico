package com.article.restgraphqlmigration.detallePedido.domain.ports.in;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import java.util.List;
import java.util.Optional;

public interface DetallePedidoInPort {
    DetallePedido createDetallePedido(DetallePedido detallePedido);

    Optional<DetallePedido> findDetallePedidoById(Long id);

    List<DetallePedido> findAllDetallePedidos();

    boolean deleteDetallePedido(Long id);
}