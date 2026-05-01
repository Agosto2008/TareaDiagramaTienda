package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.*;
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

        @NotNull(message = "La cantidad no puede ir vacía")
        @Column(name = "cantidad")
        private Integer cantidad;

        @NotNull(message = "El subtotal no puede ir vacío")
        @Column(name = "subtotal")
        private Double subtotal;

        @NotNull(message = "El código de producto no puede ir vacío")
        @Column(name = "Productos_codigo_producto")
        private Integer productosCodigoProducto;


        @NotNull(message = "El código de venta no puede ir vacío")
        @Column(name = "Ventas_codigo_venta")
        private Integer ventasCodigoVenta;
}