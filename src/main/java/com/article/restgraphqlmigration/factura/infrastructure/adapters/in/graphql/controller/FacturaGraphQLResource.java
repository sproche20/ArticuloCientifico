package com.article.restgraphqlmigration.factura.infrastructure.adapters.in.graphql.controller;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import com.article.restgraphqlmigration.factura.domain.ports.in.FacturaInPort;
import com.article.restgraphqlmigration.factura.infrastructure.adapters.in.graphql.Dto.FacturaGraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FacturaGraphQLResource {

    private final FacturaInPort facturaInPort;

    @QueryMapping
    public List<Factura> listarFacturas() {
        return facturaInPort.findAllFacturas();
    }

    @QueryMapping
    public Factura obtenerFacturaPorId(@Argument Long id) {
        return facturaInPort.findFacturaById(id).orElse(null);
    }

    @MutationMapping
    public Factura crearFactura(@Argument FacturaGraphQLRequest input) {
        return facturaInPort.createFactura(input.toDomain());
    }
}