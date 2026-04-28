package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @NotBlank(message = "El campo de nombre no puede ir vacio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @NotNull(message = "El campo de total no puede ir vacio")
    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private Boolean estado;

    @NotNull(message = "El campo de dpi de cliente no puede ir vacio")
    @Column(name = "Clientes_dpi_cliente")
    private Integer ClientesDpiCliente;

    @NotNull(message = "El campo de codigo de usuario no puede ir vacio")
    @Column(name = "Usuarios_codigo_usuario")
    private Integer UsuariosCodigoUsuario;



}
