package com.mindlift.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ejercicios")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_dificultad")
    private NivelDificultad nivelDificultad;

    @Column(name = "tipo_ejercicio", length = 100)
    private String tipoEjercicio;

    @Column(name = "zona_trabajada", length = 100)
    private String zonaTrabajada;

    @Column(columnDefinition = "TEXT")
    private String recomendaciones;

    @Column(columnDefinition = "TEXT")
    private String contraindicaciones;

    @Column(name = "tiempo_sugerido", length = 50)
    private String tiempoSugerido;

    @Column(name = "frecuencia_semanal", length = 50)
    private String frecuenciaSemanal;

    @Column(name = "material_necesario", columnDefinition = "TEXT")
    private String materialNecesario;

    @Column(name = "posicion_inicial", columnDefinition = "TEXT")
    private String posicionInicial;

    @Column(columnDefinition = "TEXT")
    private String instrucciones;

    @Column(name = "senales_alerta", columnDefinition = "TEXT")
    private String senalesAlerta;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}
