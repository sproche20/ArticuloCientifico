package com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.services;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import com.article.restgraphqlmigration.factura.domain.ports.out.FacturaOutPort;
import com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.entities.FacturaEntity;
import com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.mapper.FacturaMapper;
import com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FacturaOutAdapter implements FacturaOutPort {

    private final FacturaRepository repository;
    private final FacturaMapper mapper;

    @Override
    public Factura save(Factura factura) {
        FacturaEntity entity = mapper.toEntity(factura);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Factura> findById(Long id) {
        return repository.findByIdWithPedido(id).map(mapper::toDomain);
    }

    @Override
    public List<Factura> findAll() {
        return repository.findAllWithPedido().stream()
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
    public void saveAll(List<Factura> facturas) {
        List<FacturaEntity> entities = facturas.stream()
                .map(mapper::toEntity)
                .toList();

        repository.saveAll(entities);
    }
}