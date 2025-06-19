package com.reportes.duoc.cl.reportes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reportes.duoc.cl.reportes.dto.PedidoDTO;
import com.reportes.duoc.cl.reportes.dto.PedidoReporteDTO;
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
}

