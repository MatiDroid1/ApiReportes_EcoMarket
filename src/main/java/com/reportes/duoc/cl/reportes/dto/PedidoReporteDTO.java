package com.reportes.duoc.cl.reportes.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoReporteDTO {
    private Long pedidoId;
    //private String nombre;
    private Long clienteId;
    private String clienteNombre;
    private LocalDate fechaPedido;
    private Double total;
    private String estado;

    private List<DetalleProductoPedidoDTO> productos;
}

