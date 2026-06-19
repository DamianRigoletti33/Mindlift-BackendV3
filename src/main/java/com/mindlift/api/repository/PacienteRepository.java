package com.mindlift.api.repository;

import com.mindlift.api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNombreCompletoContainingIgnoreCaseOrRutContainingIgnoreCaseOrProblemasFisicosContainingIgnoreCase(
            String nombre, String rut, String condicion
    );
}
