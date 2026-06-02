package com.article.restgraphqlmigration.cliente.infrastructure.config;

import com.article.restgraphqlmigration.cliente.application.usecase.ClienteUseCase;
import com.article.restgraphqlmigration.cliente.domain.ports.in.ClienteInPort;
import com.article.restgraphqlmigration.cliente.domain.ports.out.ClienteOutPort;
import com.article.restgraphqlmigration.cliente.application.usecase.ClienteDatasetUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteProducer {
    @Bean
    public ClienteInPort clienteInPort(ClienteOutPort clienteOutPort) {
        return new ClienteUseCase(clienteOutPort);
    }

    @Bean
    public ClienteDatasetUseCase clienteDatasetUseCase(ClienteOutPort clienteOutPort) {
        return new ClienteDatasetUseCase(clienteOutPort);
    }
}