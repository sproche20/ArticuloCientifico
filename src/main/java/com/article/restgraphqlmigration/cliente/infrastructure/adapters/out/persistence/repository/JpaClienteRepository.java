package com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.repository;

import com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {
    @Query("SELECT c FROM ClienteEntity c LEFT JOIN FETCH c.pedidos WHERE c.id = :id")
    Optional<ClienteEntity> findByIdWithPedidos(@Param("id") Long id);

    @Query("SELECT c FROM ClienteEntity c LEFT JOIN FETCH c.pedidos")
    List<ClienteEntity> findAllWithPedidos();
}