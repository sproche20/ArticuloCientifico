package com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.in.graphql.controller;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import com.article.restgraphqlmigration.detallePedido.domain.ports.in.DetallePedidoInPort;
import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.in.graphql.Dto.DetallePedidoGraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DetallePedidoGraphQLResource {

    private final DetallePedidoInPort detallePedidoInPort;

    @QueryMapping
    public List<DetallePedido> listarDetallesPedido() {
        return detallePedidoInPort.findAllDetallePedidos();
    }

    @QueryMapping
    public DetallePedido obtenerDetallePedidoPorId(@Argument Long id) {
        return detallePedidoInPort.findDetallePedidoById(id).orElse(null);
    }

    @MutationMapping
    public DetallePedido crearDetallePedido(@Argument DetallePedidoGraphQLRequest input) {
        return detallePedidoInPort.createDetallePedido(input.toDomain());
    }
}