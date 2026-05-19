package com.marketplace.ms_carrito.repository;
import com.marketplace.ms_carrito.model.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito,Long> {
    List<ItemCarrito> findByCarritoId(Long carritoId);
    Optional<ItemCarrito> findByCarritoIdAndProductoId(Long carritoId, Long productoId);
}
