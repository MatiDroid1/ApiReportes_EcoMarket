package com.reportes.duoc.cl.reportes.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoReporteDTO {
    private Long pedidoId;
    private Long clienteId;
    private String clienteNombre;
    private LocalDate fechaPedido;
    private Double total;
    private String estado;
}

