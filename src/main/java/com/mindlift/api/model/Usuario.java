package com.mindlift.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100)
    private String username;

    @Column(length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "profesional_id")
    private Long profesionalId;

    private Boolean activo = true;
}
