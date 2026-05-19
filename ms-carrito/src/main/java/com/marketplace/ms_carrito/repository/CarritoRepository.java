package com.marketplace.ms_carrito.repository;
import com.marketplace.ms_carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CarritoRepository extends JpaRepository<Carrito,Long> {
    Optional<Carrito> findByUsuarioIdAndActivoTrue(Long usuarioId);
}
