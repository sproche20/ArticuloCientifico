package com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.services;

import com.article.restgraphqlmigration.producto.domain.model.Producto;
import com.article.restgraphqlmigration.producto.domain.ports.out.ProductoOutPort;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.mapper.ProductoMapper;
import com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductoOutAdapter implements ProductoOutPort {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = mapper.toEntity(producto);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return repository.findByIdWithDetalles(id).map(mapper::toDomain);
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAllWithDetalles().stream()
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