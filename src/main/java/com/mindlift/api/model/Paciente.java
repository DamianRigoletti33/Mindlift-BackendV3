package com.mindlift.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(length = 20, unique = true)
    private String rut;

    private Integer edad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private BigDecimal peso;
    private BigDecimal estatura;

    @Column(length = 30)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(length = 255)
    private String direccion;

    @Column(name = "contacto_emergencia", length = 150)
    private String contactoEmergencia;

    @Column(name = "telefono_emergencia", length = 30)
    private String telefonoEmergencia;

    @Column(name = "enfermedades_cronicas", columnDefinition = "TEXT")
    private String enfermedadesCronicas;

    @Column(name = "problemas_fisicos", columnDefinition = "TEXT")
    private String problemasFisicos;

    @Column(columnDefinition = "TEXT")
    private String discapacidad;

    @Column(name = "nivel_movilidad", length = 50)
    private String nivelMovilidad;

    @Column(name = "nivel_dolor")
    private Integer nivelDolor;

    @Column(name = "zona_afectada", length = 100)
    private String zonaAfectada;

    @Column(columnDefinition = "TEXT")
    private String medicamentos;

    @Column(name = "cirugias_previas", columnDefinition = "TEXT")
    private String cirugiasPrevias;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    @Column(name = "objetivo_tratamiento", columnDefinition = "TEXT")
    private String objetivoTratamiento;

    @Column(name = "frecuencia_disponible", length = 100)
    private String frecuenciaDisponible;

    @Column(name = "observaciones_medicas", columnDefinition = "TEXT")
    private String observacionesMedicas;

    @Column(columnDefinition = "TEXT")
    private String restricciones;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_riesgo")
    private NivelRiesgo nivelRiesgo;

    @Column(name = "profesional_id")
    private Long profesionalId;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;
}
