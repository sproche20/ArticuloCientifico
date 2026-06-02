package com.article.restgraphqlmigration.factura.domain.ports.out;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import java.util.List;
import java.util.Optional;

public interface FacturaOutPort {
    Factura save(Factura factura);

    Optional<Factura> findById(Long id);

    List<Factura> findAll();

    boolean deleteById(Long id);

    void saveAll(List<Factura> facturas);
}