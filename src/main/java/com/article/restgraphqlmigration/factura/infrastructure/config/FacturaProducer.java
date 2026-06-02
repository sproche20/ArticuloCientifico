package com.article.restgraphqlmigration.factura.infrastructure.config;

import com.article.restgraphqlmigration.factura.application.usecase.FacturaUseCase;
import com.article.restgraphqlmigration.factura.domain.ports.in.FacturaInPort;
import com.article.restgraphqlmigration.factura.domain.ports.out.FacturaOutPort;
import com.article.restgraphqlmigration.factura.application.usecase.FacturaDatasetUseCase;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacturaProducer {
    @Bean
    public FacturaInPort facturaInPort(FacturaOutPort facturaOutPort, PedidoOutPort pedidoOutPort) {
        return new FacturaUseCase(facturaOutPort, pedidoOutPort);
    }

    @Bean
    public FacturaDatasetUseCase facturaDatasetUseCase(FacturaOutPort facturaOutPort) {
        return new FacturaDatasetUseCase(facturaOutPort);
    }
}