package com.reportes.duoc.cl.reportes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reportes.duoc.cl.reportes.dto.PedidoReporteDTO;
import com.reportes.duoc.cl.reportes.dto.ProductoTopDTO;
import com.reportes.duoc.cl.reportes.service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/reportes")
@Tag(name = "Reportes", description = "Consultas de reportes del sistema")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/pedidos-detallados")
    @Operation(summary = "Listado detallado de pedidos con nombre del cliente")
    public ResponseEntity<List<PedidoReporteDTO>> getReportePedidos() {
        List<PedidoReporteDTO> reporte = reporteService.getPedidosDetallados();
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/productos-top")
    @Operation(summary = "Top productos más vendidos con ingreso total")
    public ResponseEntity<List<ProductoTopDTO>> getProductosTop() {
        return ResponseEntity.ok(reporteService.getProductosMasVendidos());
    }
}