package com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.services;

import com.article.restgraphqlmigration.cliente.domain.model.Cliente;
import com.article.restgraphqlmigration.cliente.domain.ports.out.ClienteOutPort;
import com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.entities.ClienteEntity;
import com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.mapper.ClienteMapper;
import com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.repository.JpaClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteOutAdapter implements ClienteOutPort {

    private final JpaClienteRepository repository;
    private final ClienteMapper mapper;

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = mapper.toEntity(cliente);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findByIdWithPedidos(id).map(mapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAllWithPedidos().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void saveAll(List<Cliente> clientes) {
        List<ClienteEntity> entities = clientes.stream()
                .map(mapper::toEntity)
                .toList();

        repository.saveAll(entities);
    }
}