package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.Productos;

import java.util.List;

public interface ProductosService {
    List<Productos> listarProductos();
    Productos crearProductos(Productos productos);
    Productos actualizarProductos(Integer id, Productos productos);
    Productos buscarPorIdProductos(Integer id);
    void eliminarProductos(Integer id);
}
