package com.mindlift.api.controller;

import com.mindlift.api.model.Profesional;
import com.mindlift.api.model.RolUsuario;
import com.mindlift.api.model.Usuario;
import com.mindlift.api.repository.ProfesionalRepository;
import com.mindlift.api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
@CrossOrigin(origins = "*")
public class ProfesionalController {
    private final ProfesionalRepository repository;
    private final UsuarioRepository usuarioRepository;

    public ProfesionalController(ProfesionalRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Profesional> listar() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Profesional> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Profesional crear(@RequestBody Profesional profesional) {
        Profesional guardado = repository.save(profesional);
        crearUsuarioProfesionalSiNoExiste(guardado);
        return guardado;
    }

    private void crearUsuarioProfesionalSiNoExiste(Profesional profesional) {
        String username;

        if (profesional.getCorreo() != null && !profesional.getCorreo().isBlank()) {
            username = profesional.getCorreo().trim();
        } else if (profesional.getId() != null) {
            username = "profesional" + profesional.getId() + "@mindlift.local";
        } else {
            return;
        }

        Usuario usuario = usuarioRepository.findByUsername(username).orElseGet(Usuario::new);
        usuario.setUsername(username);
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            usuario.setPassword("1234");
        }
        usuario.setRol(RolUsuario.PROFESIONAL);
        usuario.setPacienteId(null);
        usuario.setProfesionalId(profesional.getId());
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesional> actualizar(@PathVariable Long id, @RequestBody Profesional datos) {
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
