package com.mindlift.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "progreso")
public class Progreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "rutina_id")
    private Long rutinaId;

    @Column(name = "dolor_reportado")
    private Integer dolorReportado;

    @Column(name = "rutina_completada")
    private Boolean rutinaCompletada;

    @Column(name = "comentario_paciente", columnDefinition = "TEXT")
    private String comentarioPaciente;

    @Column(name = "comentario_profesional", columnDefinition = "TEXT")
    private String comentarioProfesional;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;
}
