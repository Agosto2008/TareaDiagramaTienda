package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {

    @Id
    @Column(name = "dpi_cliente")
    private Integer dpiCliente;

    @NotBlank(message = "El nombre no puede ir vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @NotBlank(message = "El apellido no puede ir vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Column(name = "apellido_cliente")
    private String apellidoCliente;

    @NotBlank(message = "La dirección no puede ir vacía")
    @Size(max = 100, message = "La dirección no puede sobrepasar los 100 caracteres")
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado")
    private Boolean estado;
}