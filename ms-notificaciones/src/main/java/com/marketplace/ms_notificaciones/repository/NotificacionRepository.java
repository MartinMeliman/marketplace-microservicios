package com.marketplace.ms_notificaciones.repository;
import com.marketplace.ms_notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface NotificacionRepository extends JpaRepository<Notificacion,Long> {
    List<Notificacion> findByUsuarioId(Long usuarioId);
}
