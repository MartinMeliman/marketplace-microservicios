package com.marketplace.ms_carrito.service;
import com.marketplace.ms_carrito.client.ProductoClient;
import com.marketplace.ms_carrito.dto.ItemCarritoRequestDTO;
import com.marketplace.ms_carrito.model.*;
import com.marketplace.ms_carrito.repository.*;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Slf4j @Service @RequiredArgsConstructor
public class CarritoService {
    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    private final ProductoClient productoClient;

    public Carrito obtenerOCrear(Long uid){ return carritoRepository.findByUsuarioIdAndActivoTrue(uid).orElseGet(()->{ Carrito c=new Carrito(); c.setUsuarioId(uid); c.setActivo(true); return carritoRepository.save(c); }); }
    public List<ItemCarrito> obtenerItems(Long uid){ return itemCarritoRepository.findByCarritoId(obtenerOCrear(uid).getId()); }

    public ItemCarrito agregarItem(Long uid, ItemCarritoRequestDTO dto){
        Map<String,Object> prod = validarProducto(dto.getProductoId());
        Integer stock = (Integer) prod.get("stock");
        if(stock==null||stock<dto.getCantidad()) throw new RuntimeException("Stock insuficiente. Disponible: "+stock);
        BigDecimal precio = new BigDecimal(prod.get("precio").toString());
        Carrito carrito = obtenerOCrear(uid);
        Optional<ItemCarrito> existente = itemCarritoRepository.findByCarritoIdAndProductoId(carrito.getId(),dto.getProductoId());
        if(existente.isPresent()){ ItemCarrito it=existente.get(); it.setCantidad(it.getCantidad()+dto.getCantidad()); return itemCarritoRepository.save(it); }
        ItemCarrito item=new ItemCarrito(); item.setProductoId(dto.getProductoId()); item.setCantidad(dto.getCantidad()); item.setPrecioUnitario(precio); item.setCarrito(carrito);
        log.info("Item agregado. Usuario: {}, Producto: {}", uid, dto.getProductoId());
        return itemCarritoRepository.save(item);
    }

    public void eliminarItem(Long uid, Long itemId){ itemCarritoRepository.deleteById(itemId); }
    public void vaciarCarrito(Long uid){ carritoRepository.findByUsuarioIdAndActivoTrue(uid).ifPresent(c->{ c.setActivo(false); carritoRepository.save(c); }); }

    private Map<String,Object> validarProducto(Long pid){
        try{ return productoClient.obtenerPorId(pid); }
        catch(FeignException.NotFound e){ throw new RuntimeException("Producto ID "+pid+" no existe"); }
        catch(FeignException e){ throw new RuntimeException("No se puede conectar con ms-productos"); }
    }
}
