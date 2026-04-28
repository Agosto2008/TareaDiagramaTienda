package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "detalleventa")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "codigo_detalle_venta")
        private Integer codigoDetalleVenta;

        @NotNull(message = "El campo de cantidad no puede ir vacio")
        @Column(name = "cantidad")
        private Integer cantidad;

        @NotNull(message = "El campo de subtotal no puede ir vacio")
        @Column(name = "subtotal")
        private Double subtotal;


        @NotBlank(message = "El campo de codigo de producto no puede ir vacio")
        @Column(name = "Productos_codigo_producto")
        private Integer ProductosCodigoProducto;

        @NotBlank(message = "El campo de codigo de venta no puede ir vacio")
        @Column(name = "Ventas_codigo_venta")
        private Integer VentasCodigoVenta;


}


