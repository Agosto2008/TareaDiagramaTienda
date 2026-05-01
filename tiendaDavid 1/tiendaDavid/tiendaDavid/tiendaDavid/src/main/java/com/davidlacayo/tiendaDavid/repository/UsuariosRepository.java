package com.davidlacayo.tiendaDavid.repository;

import com.davidlacayo.tiendaDavid.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Usuarios> findByUsername(String username);
    boolean existsByUsername(String username);
}