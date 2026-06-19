package com.mindlift.api.controller;

import com.mindlift.api.model.Ejercicio;
import com.mindlift.api.repository.EjercicioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
@CrossOrigin(origins = "*")
public class EjercicioController {
    private final EjercicioRepository repository;

    public EjercicioController(EjercicioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Ejercicio> listar() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ejercicio crear(@RequestBody Ejercicio ejercicio) { return repository.save(ejercicio); }

    @PutMapping("/{id}")
    public ResponseEntity<Ejercicio> actualizar(@PathVariable Long id, @RequestBody Ejercicio datos) {
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
