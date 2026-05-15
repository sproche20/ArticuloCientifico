package com.article.restgraphqlmigration.cliente.domain.ports.out;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteOutPort {
    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();

    boolean deleteById(Long id);
}