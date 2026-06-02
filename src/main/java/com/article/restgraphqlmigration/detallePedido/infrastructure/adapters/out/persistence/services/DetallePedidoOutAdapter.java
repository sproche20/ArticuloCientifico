package com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.services;

import com.article.restgraphqlmigration.detallePedido.domain.model.DetallePedido;
import com.article.restgraphqlmigration.detallePedido.domain.ports.out.DetallePedidoOutPort;
import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.entities.DetallePedidoEntity;
import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.mapper.DetallePedidoMapper;
import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.repository.DetallePedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DetallePedidoOutAdapter implements DetallePedidoOutPort {

    private final DetallePedidoRepository repository;
    private final DetallePedidoMapper mapper;

    @Override
    public DetallePedido save(DetallePedido detallePedido) {
        DetallePedidoEntity entity = mapper.toEntity(detallePedido);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<DetallePedido> findById(Long id) {
        return repository.findByIdWithRelations(id).map(mapper::toDomain);
    }

    @Override
    public List<DetallePedido> findAll() {
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

    @Override
    public void saveAll(List<DetallePedido> detalles) {
        List<DetallePedidoEntity> entities = detalles.stream()
                .map(mapper::toEntity)
                .toList();

        repository.saveAll(entities);
    }

    @Override
    public List<DetallePedido> findByPedidoId(Long pedidoId) {
        return repository.findByPedidoId(pedidoId).stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}