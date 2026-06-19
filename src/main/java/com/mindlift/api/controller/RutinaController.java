package com.mindlift.api.controller;

import com.mindlift.api.model.Rutina;
import com.mindlift.api.repository.RutinaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@CrossOrigin(origins = "*")
public class RutinaController {
    private final RutinaRepository repository;

    public RutinaController(RutinaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Rutina> listar(
            @RequestParam(required = false) Long pacienteId,
            @RequestParam(required = false) Long profesionalId
    ) {
        if (pacienteId != null) return repository.findByPacienteId(pacienteId);
        if (profesionalId != null) return repository.findByProfesionalId(profesionalId);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rutina crear(@RequestBody Rutina rutina) { return repository.save(rutina); }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> actualizar(@PathVariable Long id, @RequestBody Rutina datos) {
        return repository.findById(id).map(actual -> {
            datos.setId(actual.getId());
            return ResponseEntity.ok(repository.save(datos));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
