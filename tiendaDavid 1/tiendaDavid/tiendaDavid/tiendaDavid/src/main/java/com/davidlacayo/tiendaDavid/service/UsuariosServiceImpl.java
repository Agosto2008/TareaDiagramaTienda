package com.davidlacayo.tiendaDavid.service;


import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.exception.ResourceNotFoundException;
import com.davidlacayo.tiendaDavid.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuariosServiceImpl implements UsuariosService{
    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository, PasswordEncoder passwordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<Usuarios> listarUsuarios(){
        return usuariosRepository.findAll();
    }
    @Override
    public Usuarios crearUsuarios(Usuarios usuarios) {

        if (usuariosRepository.existsByEmail(usuarios.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        String passwordHash = passwordEncoder.encode(usuarios.getPassword());
        usuarios.setPassword(passwordHash);

        return usuariosRepository.save(usuarios);
    }
    @Override
    public Usuarios actualizarUsuarios(Integer id, Usuarios usuarios){
        Usuarios existente = buscarPorIdUsuarios(id);
        existente.setUsername(usuarios.getUsername());
        existente.setEmail(usuarios.getEmail());
        existente.setRol(usuarios.getRol());
        existente.setEstado(usuarios.getEstado());
        if (usuarios.getPassword() != null && !usuarios.getPassword().isBlank()) {
            String newPasswordHash = passwordEncoder.encode(usuarios.getPassword());
            existente.setPassword(newPasswordHash);
        }
        return usuariosRepository.save(existente);
    }
    @Override
    public Usuarios buscarPorIdUsuarios(Integer id){
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta con codigo de usuario: "+id+" no encontrada"));
    }
    @Override
    public void eliminarUsuarios(Integer id) {
        if (!usuariosRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Ruta con codigo de usuario: "+id+" no encontrada");
        }
        usuariosRepository.deleteById(id);
    }
}
