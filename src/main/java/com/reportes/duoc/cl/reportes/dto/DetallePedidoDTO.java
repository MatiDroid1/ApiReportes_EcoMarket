package com.reportes.duoc.cl.reportes.dto;

import lombok.Data;

@Data
public class DetallePedidoDTO {
    private Long productoId;
    private int cantidad;
    private double precioUnitario;
}