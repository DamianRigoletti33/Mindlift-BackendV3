package com.mindlift.api.controller;

import com.mindlift.api.model.RutinaEjercicio;
import com.mindlift.api.repository.RutinaEjercicioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutina-ejercicios")
@CrossOrigin(origins = "*")
public class RutinaEjercicioController {
    private final RutinaEjercicioRepository repository;

    public RutinaEjercicioController(RutinaEjercicioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RutinaEjercicio> listar(@RequestParam(required = false) Long rutinaId) {
        if (rutinaId != null) return repository.findByRutinaId(rutinaId);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaEjercicio> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RutinaEjercicio crear(@RequestBody RutinaEjercicio detalle) { return repository.save(detalle); }

    @PutMapping("/{id}")
    public ResponseEntity<RutinaEjercicio> actualizar(@PathVariable Long id, @RequestBody RutinaEjercicio datos) {
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
