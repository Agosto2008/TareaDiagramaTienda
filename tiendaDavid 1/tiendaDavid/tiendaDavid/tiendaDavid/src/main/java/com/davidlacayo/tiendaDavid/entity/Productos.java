package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @NotBlank(message = "El campo de nombre no puede ir vacio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotNull(message = "El campo de precio no puede ir vacio")
    @Column(name = "precio")
    private Double precio;

    @NotNull(message = "El campo de stock no puede ir vacio")
    @Column(name = "stock")
    private Integer stock;

    @Column(name = "estado")
    private Boolean estado;
}
