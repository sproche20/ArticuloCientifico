package com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.repository;

import com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    @Query("SELECT p FROM ProductoEntity p LEFT JOIN FETCH p.detalles WHERE p.id = :id")
    Optional<ProductoEntity> findByIdWithDetalles(@Param("id") Long id);

    @Query("SELECT p FROM ProductoEntity p LEFT JOIN FETCH p.detalles")
    List<ProductoEntity> findAllWithDetalles();

    @Query("SELECT p.id FROM ProductoEntity p")
    List<Long> findAllIds();
}