package com.marketplace.ms_carrito.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@FeignClient(name="ms-productos", url="${ms.productos.url}")
public interface ProductoClient {
    @GetMapping("/api/productos/{id}") Map<String,Object> obtenerPorId(@PathVariable Long id);
}
