package com.mindlift.api.repository;

import com.mindlift.api.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByPacienteId(Long pacienteId);
    List<Rutina> findByProfesionalId(Long profesionalId);
}
