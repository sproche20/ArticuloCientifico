package com.article.restgraphqlmigration.pedido.infrastructure.adapters.in.graphql.controller;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.domain.ports.in.PedidoInPort;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.in.graphql.Dto.PedidoGraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PedidoGraphQLResource {

    private final PedidoInPort pedidoInPort;

    @QueryMapping
    public List<Pedido> listarPedidos() {
        return pedidoInPort.findAllPedidos();
    }

    @QueryMapping
    public Pedido obtenerPedidoPorId(@Argument Long id) {
        return pedidoInPort.findPedidoById(id).orElse(null);
    }

    @MutationMapping
    public Pedido crearPedido(@Argument PedidoGraphQLRequest input) {
        return pedidoInPort.createPedido(input.toDomain());
    }
}