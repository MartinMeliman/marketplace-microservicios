package com.marketplace.ms_notificaciones.controller;
import com.marketplace.ms_notificaciones.dto.NotificacionDTO;
import com.marketplace.ms_notificaciones.model.Notificacion;
import com.marketplace.ms_notificaciones.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/notificaciones") @RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;
    @GetMapping("/usuario/{uid}") public List<Notificacion> porUsuario(@PathVariable Long uid){ return notificacionService.obtenerPorUsuario(uid); }
    @PostMapping("/bienvenida") public ResponseEntity<Notificacion> bienvenida(@RequestBody NotificacionDTO dto){ return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.enviarBienvenida(dto)); }
    @PostMapping("/pago")       public ResponseEntity<Notificacion> pago(@RequestBody NotificacionDTO dto){ return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.enviarPago(dto)); }
    @PostMapping("/envio")      public ResponseEntity<Notificacion> envio(@RequestBody NotificacionDTO dto){ return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.enviarEnvio(dto)); }
    @PostMapping("/pedido")     public ResponseEntity<Notificacion> pedido(@RequestBody NotificacionDTO dto){ return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.enviarPedido(dto)); }
}
