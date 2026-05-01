package com.davidlacayo.tiendaDavid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @NotBlank(message = "El nombre de usuario no puede ir vacío")
    @Size(min = 2, max = 45, message = "El username debe tener entre 2 y 45 caracteres")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "La contraseña no puede ir vacía")
    @Size(min = 8, message = "La contraseña debe tener entre 8 y 45 caracteres")
    @Column(name = "password", length = 255)
    private String password;

    @NotBlank(message = "El email no puede ir vacío")
    @Email(message = "Debe ingresar un email válido")
    @Column(name = "email")
    private String email;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private Boolean estado;
}