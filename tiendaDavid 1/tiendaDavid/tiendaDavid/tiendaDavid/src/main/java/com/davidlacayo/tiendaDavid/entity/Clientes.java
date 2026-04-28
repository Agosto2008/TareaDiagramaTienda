package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El campo de nombre no puede ir vacio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @NotBlank(message = "El campo de apellido no puede ir vacio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    @Column(name = "apellido_cliente")
    private String apellidoCliente;

    @NotBlank(message = "El campo de direccion no puede ir vacio")
    @Max(value = 100, message = "La dirreccion no puede sobrepasar los 100 caracteres")
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado")
    private Boolean estado;

}
