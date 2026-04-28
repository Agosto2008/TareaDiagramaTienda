package com.davidlacayo.tiendaDavid.repository;

import com.davidlacayo.tiendaDavid.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
}
