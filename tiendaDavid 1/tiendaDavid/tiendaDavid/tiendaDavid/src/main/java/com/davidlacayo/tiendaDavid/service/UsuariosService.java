package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.Usuarios;
import jakarta.validation.Valid;
import java.util.List;

public interface UsuariosService {
    List<Usuarios> listarUsuarios();
    Usuarios crearUsuarios(Usuarios usuarios);
    Usuarios actualizarUsuarios(Integer id, Usuarios usuarios);
    Usuarios buscarPorIdUsuarios(Integer id);
    void eliminarUsuarios(Integer id);
    boolean register(@Valid Usuarios usuarios);
}