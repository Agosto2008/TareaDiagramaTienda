package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "La fecha no puede ir vacía")
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @NotNull(message = "El total no puede ir vacío")
    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private Boolean estado;

    @NotNull(message = "El DPI del cliente no puede ir vacío")
    @Column(name = "Clientes_dpi_cliente")
    private Integer clientesDpiCliente;

    @NotNull(message = "El código de usuario no puede ir vacío")
    @Column(name = "Usuarios_codigo_usuario")
    private Integer usuariosCodigoUsuario;
}