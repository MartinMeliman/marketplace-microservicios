package com.marketplace.ms_notificaciones.service;
import com.marketplace.ms_notificaciones.dto.NotificacionDTO;
import com.marketplace.ms_notificaciones.model.Notificacion;
import com.marketplace.ms_notificaciones.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
// Servicio TRANSVERSAL: todos los microservicios lo llaman
@Slf4j @Service @RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;
    public List<Notificacion> obtenerPorUsuario(Long uid){ return notificacionRepository.findByUsuarioId(uid); }
    public Notificacion enviarBienvenida(NotificacionDTO dto){ return guardar("BIENVENIDA",dto.getUsuarioId(),dto.getEmail(),"Bienvenido a Marketplace","Hola "+dto.getNombre()+", tu cuenta fue creada exitosamente."); }
    public Notificacion enviarPago(NotificacionDTO dto){ return guardar("PAGO_APROBADO",dto.getUsuarioId(),dto.getEmail(),"Pago confirmado","Tu pago fue aprobado. Pedido: "+dto.getExtra()); }
    public Notificacion enviarEnvio(NotificacionDTO dto){ return guardar("ENVIO_ACTUALIZADO",dto.getUsuarioId(),dto.getEmail(),"Tu pedido esta en camino","Estado: "+dto.getExtra()); }
    public Notificacion enviarPedido(NotificacionDTO dto){ return guardar("PEDIDO_CONFIRMADO",dto.getUsuarioId(),dto.getEmail(),"Pedido confirmado","Tu pedido #"+dto.getExtra()+" fue confirmado."); }
    private Notificacion guardar(String tipo, Long uid, String dest, String asunto, String msg){
        Notificacion n=new Notificacion(); n.setTipo(tipo); n.setUsuarioId(uid); n.setDestinatario(dest);
        n.setAsunto(asunto); n.setMensaje(msg); n.setEnviado(true); n.setEnviadoEn(LocalDateTime.now());
        log.info("Notificacion {} guardada para usuario ID: {}", tipo, uid);
        return notificacionRepository.save(n);
    }
}
