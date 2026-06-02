package com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.in.rest.controller;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import com.article.restgraphqlmigration.detallePedido.domain.ports.in.DetallePedidoInPort;
import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.in.rest.Dto.DetallePedidoRestRequest;
import com.article.restgraphqlmigration.detallePedido.application.usecase.DetallePedidoDatasetUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rest/detalles-pedido")
@RequiredArgsConstructor
public class DetallePedidoRestResource {

    private final DetallePedidoInPort detallePedidoInPort;
    private final DetallePedidoDatasetUseCase detallePedidoDatasetUseCase;

    @GetMapping
    public List<DetallePedido> getAll() {
        return detallePedidoInPort.findAllDetallePedidos();
    }

    @GetMapping("/{id}")
    public DetallePedido getById(@PathVariable Long id) {
        return detallePedidoInPort.findDetallePedidoById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetallePedido create(@Valid @RequestBody DetallePedidoRestRequest request) {
        return detallePedidoInPort.createDetallePedido(request.toDomain());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        detallePedidoInPort.deleteDetallePedido(id);
    }

    @PostMapping("/generar")
    public ResponseEntity<String> generar(
            @RequestParam int cantidad,
            @RequestParam int maxPedidoId,
            @RequestParam int maxProductoId) {
        detallePedidoDatasetUseCase.generarDetallesMasivos(cantidad, maxPedidoId, maxProductoId);
        return ResponseEntity.ok(cantidad + " detalles generados exitosamente.");
    }
}