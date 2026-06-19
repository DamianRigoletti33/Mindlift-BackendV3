package com.mindlift.api.repository;

import com.mindlift.api.model.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgresoRepository extends JpaRepository<Progreso, Long> {
    List<Progreso> findByPacienteId(Long pacienteId);
}
