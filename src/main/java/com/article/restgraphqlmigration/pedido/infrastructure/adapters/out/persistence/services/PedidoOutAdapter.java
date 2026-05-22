package com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.services;

import com.article.restgraphqlmigration.pedido.domain.model.Pedido;
import com.article.restgraphqlmigration.pedido.domain.ports.out.PedidoOutPort;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.mapper.PedidoMapper;
import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PedidoOutAdapter implements PedidoOutPort {

    private final PedidoRepository repository;
    private final PedidoMapper mapper;

    @Override
    public Pedido save(Pedido pedido) {
        PedidoEntity entity = mapper.toEntity(pedido);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return repository.findByIdWithRelations(id).map(mapper::toDomain);
    }

    @Override
    public List<Pedido> findAll() {
        return repository.findAllWithRelations().stream()
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
}