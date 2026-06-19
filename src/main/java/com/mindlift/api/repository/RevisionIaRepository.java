package com.mindlift.api.repository;

import com.mindlift.api.model.EstadoRevisionIa;
import com.mindlift.api.model.RevisionIa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevisionIaRepository extends JpaRepository<RevisionIa, Long> {
    List<RevisionIa> findByEstado(EstadoRevisionIa estado);
    List<RevisionIa> findByPacienteId(Long pacienteId);
}
