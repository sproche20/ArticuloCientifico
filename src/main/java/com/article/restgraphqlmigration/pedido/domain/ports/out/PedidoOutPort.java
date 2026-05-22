package com.article.restgraphqlmigration.pedido.domain.ports.out;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoOutPort {
    Pedido save(Pedido pedido);

    Optional<Pedido> findById(Long id);

    List<Pedido> findAll();

    boolean deleteById(Long id);
}