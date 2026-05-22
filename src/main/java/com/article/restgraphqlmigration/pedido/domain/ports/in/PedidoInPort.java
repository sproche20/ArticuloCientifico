package com.article.restgraphqlmigration.pedido.domain.ports.in;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoInPort {
    Pedido createPedido(Pedido pedido);

    Optional<Pedido> findPedidoById(Long id);

    List<Pedido> findAllPedidos();

    Pedido updatePedido(Long id, Pedido pedido);

    boolean deletePedido(Long id);
}