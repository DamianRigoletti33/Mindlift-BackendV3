package com.mindlift.api.controller;

import com.mindlift.api.dto.LoginRequest;
import com.mindlift.api.dto.LoginResponse;
import com.mindlift.api.model.RolUsuario;
import com.mindlift.api.model.Usuario;
import com.mindlift.api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Test
    void loginProfesionalCorrectoDevuelveOkConRolEId() {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        AuthController controller = new AuthController(usuarioRepository);

        Usuario usuario = new Usuario();
        usuario.setUsername("profesional@test.cl");
        usuario.setPassword("1234");
        usuario.setRol(RolUsuario.PROFESIONAL);
        usuario.setProfesionalId(1L);
        usuario.setActivo(true);

        when(usuarioRepository.findByUsername("profesional@test.cl")).thenReturn(Optional.of(usuario));

        LoginRequest request = new LoginRequest();
        request.setUsername("profesional@test.cl");
        request.setPassword("1234");

        ResponseEntity<LoginResponse> response = controller.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("PROFESIONAL", response.getBody().getRol());
        assertEquals(1L, response.getBody().getProfesionalId());
        assertEquals("Login correcto", response.getBody().getMessage());
    }

    @Test
    void loginContrasenaIncorrectaDevuelveUnauthorized() {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        AuthController controller = new AuthController(usuarioRepository);

        Usuario usuario = new Usuario();
        usuario.setUsername("paciente@test.cl");
        usuario.setPassword("1234");
        usuario.setRol(RolUsuario.PACIENTE);
        usuario.setPacienteId(2L);
        usuario.setActivo(true);

        when(usuarioRepository.findByUsername("paciente@test.cl")).thenReturn(Optional.of(usuario));

        LoginRequest request = new LoginRequest();
        request.setUsername("paciente@test.cl");
        request.setPassword("0000");

        ResponseEntity<LoginResponse> response = controller.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("Contraseña incorrecta", response.getBody().getMessage());
    }

    @Test
    void loginUsuarioInexistenteDevuelveUnauthorized() {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        AuthController controller = new AuthController(usuarioRepository);

        when(usuarioRepository.findByUsername("noexiste@test.cl")).thenReturn(Optional.empty());

        LoginRequest request = new LoginRequest();
        request.setUsername("noexiste@test.cl");
        request.setPassword("1234");

        ResponseEntity<LoginResponse> response = controller.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("Usuario no encontrado", response.getBody().getMessage());
    }
}
