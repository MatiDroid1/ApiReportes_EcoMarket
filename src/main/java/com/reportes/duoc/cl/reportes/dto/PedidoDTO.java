package com.reportes.duoc.cl.reportes.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class PedidoDTO {
    @JsonProperty("id")
    private Long pedidoId;
    private Long clienteId;
    private LocalDate fechaPedido;
    private Double total;
    private String estado;
    private List<DetallePedidoDTO> detalles;
}
