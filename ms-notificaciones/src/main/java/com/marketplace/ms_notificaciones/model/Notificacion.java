package com.marketplace.ms_notificaciones.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor @Entity @Table(name="notificaciones")
public class Notificacion {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    // Tipos: BIENVENIDA, PAGO_APROBADO, PEDIDO_CONFIRMADO, ENVIO_ACTUALIZADO
    @Column(nullable=false, length=50) private String tipo;
    @Column(name="usuario_id", nullable=false) private Long usuarioId;
    @Column(nullable=false, length=150) private String destinatario;
    @Column(nullable=false, length=200) private String asunto;
    @Column(nullable=false, columnDefinition="TEXT") private String mensaje;
    @Column(nullable=false) private boolean enviado=false;
    @Column(name="enviado_en") private LocalDateTime enviadoEn;
    @Column(name="creado_en", updatable=false) private LocalDateTime creadoEn;
    @PrePersist public void pre(){ creadoEn=LocalDateTime.now(); }
}
