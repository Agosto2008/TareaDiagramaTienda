package com.davidlacayo.tiendaDavid.service;

import com.davidlacayo.tiendaDavid.entity.Usuarios;
import com.davidlacayo.tiendaDavid.repository.UsuariosRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private UsuariosRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuariosRepository usuarioRepository,  PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuarios usuarios = usuarioRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con nombre: " + username));

        return User.builder()
                .username(usuarios.getUsername())
                .password(usuarios.getPassword())
                .roles(usuarios.getRol() != null ? usuarios.getRol().replace("ROLE_", "") : "USER")
                .build();
    }

}