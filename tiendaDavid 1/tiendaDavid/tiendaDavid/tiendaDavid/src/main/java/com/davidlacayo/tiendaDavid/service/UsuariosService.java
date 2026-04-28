package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.Usuarios;

import java.util.List;

public interface UsuariosService {
    List<Usuarios> listarUsuarios();
    Usuarios crearUsuarios(Usuarios usuarios);
    Usuarios actualizarUsuarios(Integer id, Usuarios usuarios);
    Usuarios buscarPorIdUsuarios(Integer id);
    void eliminarUsuarios(Integer id);
}