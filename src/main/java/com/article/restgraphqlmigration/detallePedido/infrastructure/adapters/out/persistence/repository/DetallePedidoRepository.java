package com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.repository;

import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.entities.DetallePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Long> {
    @Query("SELECT dp FROM DetallePedidoEntity dp LEFT JOIN FETCH dp.pedido LEFT JOIN FETCH dp.producto WHERE dp.id = :id")
    Optional<DetallePedidoEntity> findByIdWithRelations(@Param("id") Long id);

    @Query("SELECT dp FROM DetallePedidoEntity dp LEFT JOIN FETCH dp.pedido LEFT JOIN FETCH dp.producto")
    List<DetallePedidoEntity> findAllWithRelations();

    @Query("SELECT dp FROM DetallePedidoEntity dp WHERE dp.pedido.id = :pedidoId")
    List<DetallePedidoEntity> findByPedidoId(@Param("pedidoId") Long pedidoId);
}