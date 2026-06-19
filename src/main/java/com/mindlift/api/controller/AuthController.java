package com.mindlift.api.controller;

import com.mindlift.api.dto.LoginRequest;
import com.mindlift.api.dto.LoginResponse;
import com.mindlift.api.model.Usuario;
import com.mindlift.api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body(
                    new LoginResponse(false, null, null, null, null, "Debe enviar username y password")
            );
        }

        String username = request.getUsername().trim();
        String password = request.getPassword();

        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(401).body(
                    new LoginResponse(false, null, username, null, null, "Usuario no encontrado")
            );
        }

        if (usuario.getActivo() != null && !usuario.getActivo()) {
            return ResponseEntity.status(403).body(
                    new LoginResponse(false, null, username, null, null, "Usuario inactivo")
            );
        }

        if (usuario.getPassword() == null || !usuario.getPassword().equals(password)) {
            return ResponseEntity.status(401).body(
                    new LoginResponse(false, null, username, null, null, "Contraseña incorrecta")
            );
        }

        String rol = usuario.getRol() != null ? usuario.getRol().name() : null;

        return ResponseEntity.ok(
                new LoginResponse(
                        true,
                        rol,
                        usuario.getUsername(),
                        usuario.getPacienteId(),
                        usuario.getProfesionalId(),
                        "Login correcto"
                )
        );
    }
}
