package com.article.restgraphqlmigration.pedido.infrastructure.adapters.in.rest.controller;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.domain.ports.in.PedidoInPort;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.in.rest.Dto.PedidoRestRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/pedidos")
@RequiredArgsConstructor
public class PedidoRestResource {

    private final PedidoInPort pedidoInPort;

    @GetMapping
    public List<Pedido> getAll() {
        return pedidoInPort.findAllPedidos();
    }

    @GetMapping("/{id}")
    public Pedido getById(@PathVariable Long id) {
        return pedidoInPort.findPedidoById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido create(@Valid @RequestBody PedidoRestRequest request) {
        return pedidoInPort.createPedido(request.toDomain());
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable Long id, @Valid @RequestBody PedidoRestRequest request) {
        return pedidoInPort.updatePedido(id, request.toDomainWithId(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidoInPort.deletePedido(id);
    }
}