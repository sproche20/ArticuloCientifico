package com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.repository;

import com.article.restgraphqlmigration.factura.infrastructure.adapters.out.persistence.entities.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {
    @Query("SELECT f FROM FacturaEntity f LEFT JOIN FETCH f.pedido WHERE f.id = :id")
    Optional<FacturaEntity> findByIdWithPedido(@Param("id") Long id);

    @Query("SELECT f FROM FacturaEntity f LEFT JOIN FETCH f.pedido")
    List<FacturaEntity> findAllWithPedido();
}