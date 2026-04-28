package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.Clientes;

import java.util.List;

public interface ClientesService {
    List<Clientes> listarClientes();
    Clientes crearClientes(Clientes clientes);
    Clientes actualizarClientes(Integer id, Clientes clientes);
    Clientes buscarPorIdClientes(Integer id);
    void eliminarClientes(Integer id);
}
