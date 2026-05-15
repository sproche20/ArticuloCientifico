package com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.repository;

import com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {
}