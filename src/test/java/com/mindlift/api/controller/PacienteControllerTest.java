package com.mindlift.api.controller;

import com.mindlift.api.model.Paciente;
import com.mindlift.api.model.RolUsuario;
import com.mindlift.api.model.Usuario;
import com.mindlift.api.repository.PacienteRepository;
import com.mindlift.api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PacienteControllerTest {

    @Test
    void crearPacienteCreaUsuarioAutomaticoConRolPaciente() {
        PacienteRepository pacienteRepository = mock(PacienteRepository.class);
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        PacienteController controller = new PacienteController(pacienteRepository, usuarioRepository);

        Paciente input = new Paciente();
        input.setNombreCompleto("Paciente Prueba");
        input.setRut("11111111-1");
        input.setCorreo("paciente@test.cl");

        when(pacienteRepository.save(any(Paciente.class))).thenAnswer(invocation -> {
            Paciente p = invocation.getArgument(0);
            p.setId(20L);
            return p;
        });
        when(usuarioRepository.findByUsername("paciente@test.cl")).thenReturn(Optional.empty());

        Paciente creado = controller.crear(input);

        assertEquals(20L, creado.getId());
        ArgumentCaptor<Usuario> usuarioCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario usuario = usuarioCaptor.getValue();
        assertEquals("paciente@test.cl", usuario.getUsername());
        assertEquals("1234", usuario.getPassword());
        assertEquals(RolUsuario.PACIENTE, usuario.getRol());
        assertEquals(20L, usuario.getPacienteId());
        assertNull(usuario.getProfesionalId());
        assertTrue(usuario.getActivo());
    }
}
