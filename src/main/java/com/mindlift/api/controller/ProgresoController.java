package com.mindlift.api.controller;

import com.mindlift.api.model.Progreso;
import com.mindlift.api.repository.ProgresoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progreso")
@CrossOrigin(origins = "*")
public class ProgresoController {
    private final ProgresoRepository repository;

    public ProgresoController(ProgresoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Progreso> listar(@RequestParam(required = false) Long pacienteId) {
        if (pacienteId != null) return repository.findByPacienteId(pacienteId);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progreso> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Progreso crear(@RequestBody Progreso progreso) { return repository.save(progreso); }

    @PutMapping("/{id}")
    public ResponseEntity<Progreso> actualizar(@PathVariable Long id, @RequestBody Progreso datos) {
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
