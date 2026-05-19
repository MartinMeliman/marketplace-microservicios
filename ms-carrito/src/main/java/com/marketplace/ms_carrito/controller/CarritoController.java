package com.marketplace.ms_carrito.controller;
import com.marketplace.ms_carrito.dto.ItemCarritoRequestDTO;
import com.marketplace.ms_carrito.model.*;
import com.marketplace.ms_carrito.service.CarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/carrito") @RequiredArgsConstructor
public class CarritoController {
    private final CarritoService carritoService;
    @GetMapping("/{uid}") public List<ItemCarrito> obtenerItems(@PathVariable Long uid){ return carritoService.obtenerItems(uid); }
    @PostMapping("/{uid}/items") public ResponseEntity<ItemCarrito> agregarItem(@PathVariable Long uid, @Valid @RequestBody ItemCarritoRequestDTO dto){ return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.agregarItem(uid,dto)); }
    @DeleteMapping("/{uid}/items/{itemId}") public ResponseEntity<Void> eliminarItem(@PathVariable Long uid, @PathVariable Long itemId){ carritoService.eliminarItem(uid,itemId); return ResponseEntity.noContent().build(); }
    @DeleteMapping("/{uid}") public ResponseEntity<Void> vaciarCarrito(@PathVariable Long uid){ carritoService.vaciarCarrito(uid); return ResponseEntity.noContent().build(); }
}
