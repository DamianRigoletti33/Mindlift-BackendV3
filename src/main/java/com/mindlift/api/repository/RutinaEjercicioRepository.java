package com.mindlift.api.repository;

import com.mindlift.api.model.RutinaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicio, Long> {
    List<RutinaEjercicio> findByRutinaId(Long rutinaId);
}
