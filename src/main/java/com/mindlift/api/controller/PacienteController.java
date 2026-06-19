package com.mindlift.api.controller;

import com.mindlift.api.model.Paciente;
import com.mindlift.api.model.RolUsuario;
import com.mindlift.api.model.Usuario;
import com.mindlift.api.repository.PacienteRepository;
import com.mindlift.api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {
    private final PacienteRepository repository;
    private final UsuarioRepository usuarioRepository;

    public PacienteController(PacienteRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Paciente> listar(@RequestParam(required = false) String buscar) {
        if (buscar == null || buscar.isBlank()) return repository.findAll();
        return repository.findByNombreCompletoContainingIgnoreCaseOrRutContainingIgnoreCaseOrProblemasFisicosContainingIgnoreCase(buscar, buscar, buscar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente crear(@RequestBody Paciente paciente) {
        Paciente guardado = repository.save(paciente);
        crearUsuarioPacienteSiNoExiste(guardado);
        return guardado;
    }

    private void crearUsuarioPacienteSiNoExiste(Paciente paciente) {
        String username = null;

        if (paciente.getCorreo() != null && !paciente.getCorreo().isBlank()) {
            username = paciente.getCorreo().trim();
        } else if (paciente.getRut() != null && !paciente.getRut().isBlank()) {
            username = paciente.getRut().trim() + "@mindlift.local";
        } else if (paciente.getId() != null) {
            username = "paciente" + paciente.getId() + "@mindlift.local";
        }

        if (username == null || username.isBlank()) return;

        Usuario usuario = usuarioRepository.findByUsername(username).orElseGet(Usuario::new);
        usuario.setUsername(username);
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            usuario.setPassword("1234");
        }
        usuario.setRol(RolUsuario.PACIENTE);
        usuario.setPacienteId(paciente.getId());
        usuario.setProfesionalId(null);
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente datos) {
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
