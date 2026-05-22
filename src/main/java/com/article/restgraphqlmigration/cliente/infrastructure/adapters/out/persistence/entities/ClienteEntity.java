package com.article.restgraphqlmigration.cliente.infrastructure.adapters.out.persistence.entities;

import com.article.restgraphqlmigration.pedido.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
    // relacion bidireccional con pedidos:un cliente puede tener muchos pedidos
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    public List<PedidoEntity> pedidos = new ArrayList<>();

}