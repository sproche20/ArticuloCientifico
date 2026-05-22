package com.article.restgraphqlmigration.factura.infrastructure.adapters.in.rest.controller;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import com.article.restgraphqlmigration.factura.domain.ports.in.FacturaInPort;
import com.article.restgraphqlmigration.factura.infrastructure.adapters.in.rest.Dto.FacturaRestRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rest/facturas")
@RequiredArgsConstructor
public class FacturaRestResource {

    private final FacturaInPort facturaInPort;

    @GetMapping
    public List<Factura> getAll() {
        return facturaInPort.findAllFacturas();
    }

    @GetMapping("/{id}")
    public Factura getById(@PathVariable Long id) {
        return facturaInPort.findFacturaById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Factura create(@Valid @RequestBody FacturaRestRequest request) {
        return facturaInPort.createFactura(request.toDomain());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facturaInPort.deleteFactura(id);
    }
}