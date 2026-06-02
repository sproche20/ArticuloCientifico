package com.article.restgraphqlmigration.detallePedido.infrastructure.config;

import com.article.restgraphqlmigration.detallePedido.application.usecase.DetallePedidoUseCase;
import com.article.restgraphqlmigration.detallePedido.domain.ports.in.DetallePedidoInPort;
import com.article.restgraphqlmigration.detallePedido.domain.ports.out.DetallePedidoOutPort;
import com.article.restgraphqlmigration.detallePedido.application.usecase.DetallePedidoDatasetUseCase;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetallePedidoProducer {
    @Bean
    public DetallePedidoInPort detallePedidoInPort(DetallePedidoOutPort detallePedidoOutPort,
            PedidoOutPort pedidoOutPort) {
        return new DetallePedidoUseCase(detallePedidoOutPort, pedidoOutPort);
    }

    @Bean
    public DetallePedidoDatasetUseCase detallePedidoDatasetUseCase(DetallePedidoOutPort detallePedidoOutPort) {
        return new DetallePedidoDatasetUseCase(detallePedidoOutPort);
    }
}