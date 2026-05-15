package com.article.restgraphqlmigration.cliente.infrastructure.adapters.in.graphql.controller;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import com.article.restgraphqlmigration.cliente.domain.ports.in.ClienteInPort;
import com.article.restgraphqlmigration.cliente.infrastructure.adapters.in.graphql.Dto.ClienteGraphQLRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClienteGraphQLResource {

    private final ClienteInPort clienteInPort;

    // El nombre del método debe coincidir con el nombre del campo en el archivo
    // .graphqls
    @QueryMapping
    public List<Cliente> listarClientes() {
        return clienteInPort.findAllClientes();
    }

    @QueryMapping
    public Cliente obtenerClientePorId(@Argument Long id) {
        return clienteInPort.findClienteById(id).orElse(null);
    }

    @MutationMapping
    public Cliente crearCliente(@Argument ClienteGraphQLRequest input) {
        return clienteInPort.createCliente(input.toDomain());
    }
}