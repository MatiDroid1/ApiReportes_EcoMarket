package com.reportes.duoc.cl.reportes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductoDTO {

    @JsonProperty("id")
    private Long productoId;

    private String nombre;
    private double precio;
}
