package com.reportes.duoc.cl.reportes.dto;


import lombok.Data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class PedidoDTO {
     @JsonProperty("id") // 👈 Esto es clave
    private Long pedidoId;
    private Long clienteId;
    private LocalDate fechaPedido;
    private Double total;
    private String estado;
}
