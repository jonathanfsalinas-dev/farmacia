package com.farmacia.entity;

import com.farmacia.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

// entity le dice a jpa: esta clase representa una tabla
@Entity
// le dice a jpa como se llama la tabla en la base de datos
@Table(name = "usuarios")
//anotaciones de lombok para generar codigo automaticamente
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    // le dice a jpa que esta variable es la clave primaria
    @Id
    // le dice a jpa que el valor de esta variable se genera automaticamente por la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //columm permite configurar restricciones de la columna en la base de datos
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    //guarde el enum como texto , ejemplo ADMIN
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Column(nullable = false)
    // valor por defecto true, el usuario se crea activo
    @Builder.Default
    private Boolean activo = true;

    @Column(nullable = false)
    @Builder.Default
    private Boolean bloqueado = false;
    
}