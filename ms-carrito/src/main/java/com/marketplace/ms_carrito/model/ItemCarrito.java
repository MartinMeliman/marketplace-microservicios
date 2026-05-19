package com.marketplace.ms_carrito.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name="items_carrito", uniqueConstraints=@UniqueConstraint(columnNames={"carrito_id","producto_id"}))
public class ItemCarrito {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="producto_id", nullable=false) private Long productoId;
    @Column(nullable=false) private Integer cantidad;
    // Precio congelado al momento de agregar al carrito
    @Column(name="precio_unitario", nullable=false, precision=10, scale=2) private BigDecimal precioUnitario;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="carrito_id", nullable=false) private Carrito carrito;
}
