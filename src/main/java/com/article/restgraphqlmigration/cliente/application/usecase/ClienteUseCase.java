package com.article.restgraphqlmigration.cliente.application.usecase;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import com.article.restgraphqlmigration.cliente.domain.ports.in.ClienteInPort;
import com.article.restgraphqlmigration.cliente.domain.ports.out.ClienteOutPort;
import java.util.List;
import java.util.Optional;

public class ClienteUseCase implements ClienteInPort {

    private final ClienteOutPort clienteOutPort;

    public ClienteUseCase(ClienteOutPort clienteOutPort) {
        this.clienteOutPort = clienteOutPort;
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteOutPort.save(cliente);
    }

    @Override
    public Optional<Cliente> findClienteById(Long id) {
        return clienteOutPort.findById(id);
    }

    @Override
    public List<Cliente> findAllClientes() {
        return clienteOutPort.findAll();
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        // En un Record, para "actualizar" debemos crear una nueva instancia con el ID
        return clienteOutPort.save(cliente);
    }

    @Override
    public boolean deleteCliente(Long id) {
        return clienteOutPort.deleteById(id);
    }
}