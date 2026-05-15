package com.article.restgraphqlmigration.cliente.domain.ports.in;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteInPort {
    Cliente createCliente(Cliente cliente);

    Optional<Cliente> findClienteById(Long id);

    List<Cliente> findAllClientes();

    Cliente updateCliente(Long id, Cliente cliente);

    boolean deleteCliente(Long id);
}