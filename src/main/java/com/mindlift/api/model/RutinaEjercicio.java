package com.mindlift.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rutina_ejercicios")
public class RutinaEjercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rutina_id", nullable = false)
    private Long rutinaId;

    @Column(name = "ejercicio_id", nullable = false)
    private Long ejercicioId;

    private Integer series;
    private Integer repeticiones;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
