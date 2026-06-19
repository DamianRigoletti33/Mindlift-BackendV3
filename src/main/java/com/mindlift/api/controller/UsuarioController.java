package com.mindlift.api.controller;

import com.mindlift.api.model.Usuario;
import com.mindlift.api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Usuario> listar() { return repository.findAll(); }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) { return repository.save(usuario); }

    @PostMapping("/login-demo")
    public ResponseEntity<?> loginDemo(@RequestBody Map<String, String> body) {
        String username = body.getOrDefault("username", "demo@mindlift.cl");
        return repository.findByUsername(username)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok(Map.of(
                        "username", username,
                        "mensaje", "Login demo local aceptado. Usuario no existe en BD, pero puede continuar como demo."
                )));
    }
}
