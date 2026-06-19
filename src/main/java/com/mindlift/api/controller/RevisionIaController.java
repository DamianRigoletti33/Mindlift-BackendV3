package com.mindlift.api.controller;

import com.mindlift.api.model.EstadoRevisionIa;
import com.mindlift.api.model.RevisionIa;
import com.mindlift.api.repository.RevisionIaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revision-ia")
@CrossOrigin(origins = "*")
public class RevisionIaController {
    private final RevisionIaRepository repository;

    public RevisionIaController(RevisionIaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RevisionIa> listar(
            @RequestParam(required = false) EstadoRevisionIa estado,
            @RequestParam(required = false) Long pacienteId
    ) {
        if (estado != null) return repository.findByEstado(estado);
        if (pacienteId != null) return repository.findByPacienteId(pacienteId);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevisionIa> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RevisionIa crear(@RequestBody RevisionIa revision) { return repository.save(revision); }

    @PutMapping("/{id}")
    public ResponseEntity<RevisionIa> actualizar(@PathVariable Long id, @RequestBody RevisionIa datos) {
        return repository.findById(id).map(actual -> {
            datos.setId(actual.getId());
            return ResponseEntity.ok(repository.save(datos));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<RevisionIa> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoRevisionIa estado,
            @RequestParam(required = false) String respuestaFinal,
            @RequestParam(required = false) String observacionProfesional
    ) {
        return repository.findById(id).map(actual -> {
            actual.setEstado(estado);
            if (respuestaFinal != null) actual.setRespuestaFinal(respuestaFinal);
            if (observacionProfesional != null) actual.setObservacionProfesional(observacionProfesional);
            return ResponseEntity.ok(repository.save(actual));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
