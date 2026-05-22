package com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.repository;

import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    @Query("SELECT p FROM PedidoEntity p LEFT JOIN FETCH p.cliente LEFT JOIN FETCH p.detalles dp LEFT JOIN FETCH dp.producto LEFT JOIN FETCH p.factura WHERE p.id = :id")
    Optional<PedidoEntity> findByIdWithRelations(@Param("id") Long id);

    @Query("SELECT p FROM PedidoEntity p LEFT JOIN FETCH p.cliente LEFT JOIN FETCH p.detalles dp LEFT JOIN FETCH dp.producto LEFT JOIN FETCH p.factura")
    List<PedidoEntity> findAllWithRelations();
}