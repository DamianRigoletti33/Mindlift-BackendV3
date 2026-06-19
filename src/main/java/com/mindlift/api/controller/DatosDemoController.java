package com.mindlift.api.controller;

import com.mindlift.api.model.*;
import com.mindlift.api.repository.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
@CrossOrigin(origins = "*")
public class DatosDemoController {
    private final ProfesionalRepository profesionalRepository;
    private final PacienteRepository pacienteRepository;
    private final EjercicioRepository ejercicioRepository;
    private final RutinaRepository rutinaRepository;
    private final ProgresoRepository progresoRepository;
    private final RevisionIaRepository revisionIaRepository;

    public DatosDemoController(
            ProfesionalRepository profesionalRepository,
            PacienteRepository pacienteRepository,
            EjercicioRepository ejercicioRepository,
            RutinaRepository rutinaRepository,
            ProgresoRepository progresoRepository,
            RevisionIaRepository revisionIaRepository
    ) {
        this.profesionalRepository = profesionalRepository;
        this.pacienteRepository = pacienteRepository;
        this.ejercicioRepository = ejercicioRepository;
        this.rutinaRepository = rutinaRepository;
        this.progresoRepository = progresoRepository;
        this.revisionIaRepository = revisionIaRepository;
    }

    @PostMapping("/cargar")
    public Map<String, String> cargarDatosDemo() {
        Profesional profesional = new Profesional();
        profesional.setNombreCompleto("Klgo. Sebastián Morales");
        profesional.setEspecialidad("Rehabilitación musculoesquelética");
        profesional.setNumeroRegistro("SIM-2026-001");
        profesional.setCorreo("profesional@mindlift.cl");
        profesional.setTelefono("+56 9 1234 5678");
        profesional.setAreaExperiencia("Adultos mayores, movilidad reducida y dolor crónico");
        profesional.setDisponibilidad("Lunes a viernes, 09:00 a 17:00");
        profesional = profesionalRepository.save(profesional);

        Paciente paciente = new Paciente();
        paciente.setNombreCompleto("María González");
        paciente.setRut("12.345.678-9");
        paciente.setEdad(68);
        paciente.setPeso(new BigDecimal("72.00"));
        paciente.setEstatura(new BigDecimal("1.58"));
        paciente.setTelefono("+56 9 1111 2222");
        paciente.setCorreo("maria@correo.cl");
        paciente.setEnfermedadesCronicas("Artrosis, hipertensión");
        paciente.setProblemasFisicos("Dolor de rodilla derecha");
        paciente.setNivelMovilidad("Media");
        paciente.setNivelDolor(4);
        paciente.setZonaAfectada("Rodilla derecha");
        paciente.setObjetivoTratamiento("Mejorar marcha y reducir dolor");
        paciente.setNivelRiesgo(NivelRiesgo.MEDIO);
        paciente.setProfesionalId(profesional.getId());
        paciente = pacienteRepository.save(paciente);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre("Movilidad de tobillo sentado");
        ejercicio.setDescripcion("Movimiento suave para activar circulación y movilidad articular.");
        ejercicio.setNivelDificultad(NivelDificultad.BAJO);
        ejercicio.setTipoEjercicio("Movilidad");
        ejercicio.setZonaTrabajada("Tobillo");
        ejercicio.setTiempoSugerido("8 minutos");
        ejercicio.setFrecuenciaSemanal("4 veces por semana");
        ejercicio.setMaterialNecesario("Silla firme");
        ejercicio.setSenalesAlerta("Detener si aparece dolor intenso o mareo.");
        ejercicio = ejercicioRepository.save(ejercicio);

        Rutina rutina = new Rutina();
        rutina.setPacienteId(paciente.getId());
        rutina.setProfesionalId(profesional.getId());
        rutina.setNombre("Rutina segura de rodilla");
        rutina.setDescripcion("Rutina inicial de rehabilitación con baja carga.");
        rutina.setFrecuencia("3 veces por semana");
        rutina.setObservaciones("Ejercicios controlados, sin impacto.");
        rutinaRepository.save(rutina);

        Progreso progreso = new Progreso();
        progreso.setPacienteId(paciente.getId());
        progreso.setDolorReportado(3);
        progreso.setRutinaCompletada(true);
        progreso.setComentarioPaciente("Completó rutina con molestia leve.");
        progresoRepository.save(progreso);

        RevisionIa revision = new RevisionIa();
        revision.setPacienteId(paciente.getId());
        revision.setProfesionalId(profesional.getId());
        revision.setPreguntaPaciente("¿Puedo aumentar las repeticiones si no siento dolor?");
        revision.setRespuestaIa("Mantener la carga actual una semana más y consultar evolución.");
        revision.setEstado(EstadoRevisionIa.PENDIENTE);
        revisionIaRepository.save(revision);

        return Map.of("mensaje", "Datos demo cargados correctamente");
    }
}
