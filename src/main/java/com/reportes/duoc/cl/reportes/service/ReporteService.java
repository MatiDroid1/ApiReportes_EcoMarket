package com.reportes.duoc.cl.reportes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reportes.duoc.cl.reportes.dto.DetallePedidoDTO;
import com.reportes.duoc.cl.reportes.dto.PedidoDTO;
import com.reportes.duoc.cl.reportes.dto.PedidoReporteDTO;
import com.reportes.duoc.cl.reportes.dto.ProductoDTO;
import com.reportes.duoc.cl.reportes.dto.ProductoTopDTO;
import com.reportes.duoc.cl.reportes.dto.UsuarioDTO;
import com.reportes.duoc.cl.reportes.util.ApiKeyRestClient;

import org.springframework.beans.factory.annotation.Value;

@Service
public class ReporteService {

    @Autowired
    private ApiKeyRestClient api;

    @Value("${url.api.pedidos}")
    private String urlPedidos;

    @Value("${url.api.usuarios}")
    private String urlUsuarios;

    @Value("${api.key.pedidos}")
    private String apiKeyPedidos;

    @Value("${api.key.usuarios}")
    private String apiKeyUsuarios;

    @Value("${url.api.productos}")
private String urlProductos;

@Value("${api.key.productos}")
private String apiKeyProductos;

    public List<PedidoReporteDTO> getPedidosDetallados() {
        PedidoDTO[] pedidos = api.get(urlPedidos, apiKeyPedidos, PedidoDTO[].class);
        List<PedidoReporteDTO> reporte = new ArrayList<>();

        if (pedidos != null) {
            for (PedidoDTO p : pedidos) {
                PedidoReporteDTO dto = new PedidoReporteDTO();
                dto.setPedidoId(p.getPedidoId());
                dto.setClienteId(p.getClienteId());
                dto.setFechaPedido(p.getFechaPedido());
                dto.setTotal(p.getTotal());
                dto.setEstado(p.getEstado());

                try {
                    UsuarioDTO u = api.get(urlUsuarios + p.getClienteId(), apiKeyUsuarios, UsuarioDTO.class);
                    dto.setClienteNombre(u.getNombre());
                } catch (Exception e) {
                    dto.setClienteNombre("No disponible");
                }

                reporte.add(dto);
            }
        }

        return reporte;
    }

/*servicio para los + vendidos*/
public List<ProductoTopDTO> getProductosMasVendidos() {
    PedidoDTO[] pedidos = api.get(urlPedidos, apiKeyPedidos, PedidoDTO[].class);
    Map<Long, Integer> ventasPorProducto = new HashMap<>();

    if (pedidos != null) {
        for (PedidoDTO pedido : pedidos) {
            if (pedido.getDetalles() != null) {
                for (DetallePedidoDTO detalle : pedido.getDetalles()) {
                    ventasPorProducto.merge(detalle.getProductoId(), detalle.getCantidad(), Integer::sum);
                }
            }
        }
    }

    List<ProductoTopDTO> topProductos = new ArrayList<>();

    for (Map.Entry<Long, Integer> entry : ventasPorProducto.entrySet()) {
        Long productoId = entry.getKey();
        int cantidad = entry.getValue();

        try {
            ProductoDTO producto = api.get(urlProductos + "/" + productoId, apiKeyProductos, ProductoDTO.class);

            ProductoTopDTO dto = new ProductoTopDTO();
            dto.setProductoId(productoId);
            dto.setNombre(producto.getNombre());
            dto.setCantidadVendida(cantidad);
            dto.setPrecioUnitario(producto.getPrecio());
            dto.setIngresoTotal(cantidad * producto.getPrecio());

            topProductos.add(dto);
        } catch (Exception e) {
            // producto no encontrado, lo ignoramos
        }
    }

    return topProductos;
}
}

