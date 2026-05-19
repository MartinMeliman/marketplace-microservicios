package com.marketplace.ms_carrito.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name="carritos")
public class Carrito {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="usuario_id", nullable=false, unique=true) private Long usuarioId;
    @Column(nullable=false) private boolean activo=true;
    @Column(name="creado_en", updatable=false) private LocalDateTime creadoEn;
    @OneToMany(mappedBy="carrito", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private List<ItemCarrito> items;
    @PrePersist public void pre(){ creadoEn=LocalDateTime.now(); }
}
