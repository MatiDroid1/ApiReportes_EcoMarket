package com.reportes.duoc.cl.reportes.dto;

import lombok.Data;

@Data
public class DetalleProductoPedidoDTO {
    private Long productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
}