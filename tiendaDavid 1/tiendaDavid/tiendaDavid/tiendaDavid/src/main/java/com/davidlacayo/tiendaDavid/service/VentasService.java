package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.Ventas;

import java.util.List;

public interface VentasService {
    List<Ventas> listarVentas();
    Ventas crearVentas(Ventas ventas);
    Ventas actualizarVentas(Integer id, Ventas ventas);
    Ventas buscarPorIdVentas(Integer id);
    void eliminarVentas(Integer id);
}
