package com.davidlacayo.tiendaDavid.repository;

import com.davidlacayo.tiendaDavid.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {
}
