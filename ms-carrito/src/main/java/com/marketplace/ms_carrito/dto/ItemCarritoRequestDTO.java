package com.marketplace.ms_carrito.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor
public class ItemCarritoRequestDTO {
    @NotNull(message="El productoId es obligatorio") private Long productoId;
    @NotNull @Min(value=1, message="La cantidad minima es 1") private Integer cantidad;
}
