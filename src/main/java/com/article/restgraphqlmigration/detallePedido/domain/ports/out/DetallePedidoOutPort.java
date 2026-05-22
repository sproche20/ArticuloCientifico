package com.article.restgraphqlmigration.detallePedido.domain.ports.out;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import java.util.List;
import java.util.Optional;

public interface DetallePedidoOutPort {
    DetallePedido save(DetallePedido detallePedido);

    Optional<DetallePedido> findById(Long id);

    List<DetallePedido> findAll();

    boolean deleteById(Long id);

    List<DetallePedido> findByPedidoId(Long pedidoId);
}