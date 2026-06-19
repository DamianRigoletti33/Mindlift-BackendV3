package com.mindlift.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profesionales")
public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(length = 100)
    private String especialidad;

    @Column(name = "numero_registro", length = 50)
    private String numeroRegistro;

    @Column(length = 100, unique = true)
    private String correo;

    @Column(length = 30)
    private String telefono;

    @Column(name = "area_experiencia", columnDefinition = "TEXT")
    private String areaExperiencia;

    @Column(columnDefinition = "TEXT")
    private String disponibilidad;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}
