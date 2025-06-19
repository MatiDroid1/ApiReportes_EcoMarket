package com.reportes.duoc.cl.reportes.dto;

import lombok.Data;

@Data
public class ProductoTopDTO {
    private Long productoId;
    private String nombre;
    private int cantidadVendida;
    private double precioUnitario;
    private double ingresoTotal;
}