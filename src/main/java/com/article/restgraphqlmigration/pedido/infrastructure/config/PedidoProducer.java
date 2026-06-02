package com.article.restgraphqlmigration.pedido.infrastructure.config;

import com.article.restgraphqlmigration.pedido.application.usecase.PedidoUseCase;
import com.article.restgraphqlmigration.pedido.domain.ports.in.PedidoInPort;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import com.article.restgraphqlmigration.detallePedido.domain.ports.out.DetallePedidoOutPort;
import com.article.restgraphqlmigration.pedido.application.usecase.PedidoDatasetUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoProducer {
    @Bean
    public PedidoInPort pedidoInPort(PedidoOutPort pedidoOutPort, DetallePedidoOutPort detallePedidoOutPort) {
        return new PedidoUseCase(pedidoOutPort, detallePedidoOutPort);
    }

    @Bean
    public PedidoDatasetUseCase pedidoDatasetUseCase(PedidoOutPort pedidoOutPort) {
        return new PedidoDatasetUseCase(pedidoOutPort);
    }
}