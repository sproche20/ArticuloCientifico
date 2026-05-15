package com.article.restgraphqlmigration.cliente.infrastructure.adapters.in.rest.controller;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import com.article.restgraphqlmigration.cliente.domain.ports.in.ClienteInPort;
import com.article.restgraphqlmigration.cliente.infrastructure.adapters.in.rest.Dto.ClienteRestRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/clientes")
@RequiredArgsConstructor
public class ClienteRestResource {

    private final ClienteInPort clienteInPort;

    @GetMapping
    public List<Cliente> getAll() {
        return clienteInPort.findAllClientes();
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Long id) {
        return clienteInPort.findClienteById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@Valid @RequestBody ClienteRestRequest request) {
        return clienteInPort.createCliente(request.toDomain());
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @Valid @RequestBody ClienteRestRequest request) {
        return clienteInPort.updateCliente(id, request.toDomainWithId(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clienteInPort.deleteCliente(id);
    }
}