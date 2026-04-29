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

    @NotBlank(message = "El campo de nombre no puede ir vacio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "El campo de contraseña no puede ir vacio")
    @Size(min = 8, max = 150, message = "El contraseña debe tener entre 8 y 150 caracteres")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "El campo de direccion no puede ir vacio")
    @Size(min = 1, max = 100, message = "La dirreccion no puede sobrepasar los 100 caracteres")
    @Column(name = "email")
    @Email
    private String email;

    @NotBlank(message = "El campo de direccion no puede ir vacio")
    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private Boolean estado;

}
