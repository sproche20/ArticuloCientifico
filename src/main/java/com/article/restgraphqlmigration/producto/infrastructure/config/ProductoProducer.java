package com.article.restgraphqlmigration.producto.infrastructure.config;

import com.article.restgraphqlmigration.producto.application.usecase.ProductoUseCase;
import com.article.restgraphqlmigration.producto.domain.ports.in.ProductoInPort;
import com.article.restgraphqlmigration.producto.domain.ports.out.ProductoOutPort;
import com.article.restgraphqlmigration.producto.application.usecase.ProductoDatasetUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductoProducer {
    @Bean
    public ProductoInPort productoInPort(ProductoOutPort productoOutPort) {
        return new ProductoUseCase(productoOutPort);
    }

    @Bean
    public ProductoDatasetUseCase productoDatasetUseCase(ProductoOutPort productoOutPort) {
        return new ProductoDatasetUseCase(productoOutPort);
    }
}