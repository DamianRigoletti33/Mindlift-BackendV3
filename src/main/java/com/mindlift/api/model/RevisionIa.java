package com.mindlift.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "revision_ia")
public class RevisionIa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "profesional_id")
    private Long profesionalId;

    @Column(name = "pregunta_paciente", columnDefinition = "TEXT")
    private String preguntaPaciente;

    @Column(name = "respuesta_ia", columnDefinition = "TEXT")
    private String respuestaIa;

    @Column(name = "respuesta_final", columnDefinition = "TEXT")
    private String respuestaFinal;

    @Enumerated(EnumType.STRING)
    private EstadoRevisionIa estado = EstadoRevisionIa.PENDIENTE;

    @Column(name = "observacion_profesional", columnDefinition = "TEXT")
    private String observacionProfesional;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}
