package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> listarDetalleVentas();
    DetalleVenta crearDetalleVenta(DetalleVenta detalleVenta);
    DetalleVenta actualizarDetalleVenta(Integer id,DetalleVenta detalleVenta);
    DetalleVenta buscarPorIdDetalleVenta(Integer id);
    void eliminarDetalleVenta(Integer id);
}
