package com.article.restgraphqlmigration.producto.infrastructure.adapters.out.persistence.entities;

import com.article.restgraphqlmigration.detallePedido.infrastructure.adapters.out.persistence.entities.DetallePedidoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Boolean activo;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetallePedidoEntity> detalles = new ArrayList<>();
}