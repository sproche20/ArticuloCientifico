package com.article.restgraphqlmigration.factura.domain.ports.in;

import com.article.restgraphqlmigration.factura.domain.model.Factura;
import java.util.List;
import java.util.Optional;

public interface FacturaInPort {
    Factura createFactura(Factura factura);

    Optional<Factura> findFacturaById(Long id);

    List<Factura> findAllFacturas();

    boolean deleteFactura(Long id);
}