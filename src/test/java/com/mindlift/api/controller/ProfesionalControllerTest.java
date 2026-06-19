package com.mindlift.api.controller;

import com.mindlift.api.model.Profesional;
import com.mindlift.api.model.RolUsuario;
import com.mindlift.api.model.Usuario;
import com.mindlift.api.repository.ProfesionalRepository;
import com.mindlift.api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProfesionalControllerTest {

    @Test
    void crearProfesionalCreaUsuarioAutomaticoConRolProfesional() {
        ProfesionalRepository profesionalRepository = mock(ProfesionalRepository.class);
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        ProfesionalController controller = new ProfesionalController(profesionalRepository, usuarioRepository);

        Profesional input = new Profesional();
        input.setNombreCompleto("Damian");
        input.setEspecialidad("Kine");
        input.setCorreo("damian@test.cl");

        when(profesionalRepository.save(any(Profesional.class))).thenAnswer(invocation -> {
            Profesional p = invocation.getArgument(0);
            p.setId(10L);
            return p;
        });
        when(usuarioRepository.findByUsername("damian@test.cl")).thenReturn(Optional.empty());

        Profesional creado = controller.crear(input);

        assertEquals(10L, creado.getId());
        ArgumentCaptor<Usuario> usuarioCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario usuario = usuarioCaptor.getValue();
        assertEquals("damian@test.cl", usuario.getUsername());
        assertEquals("1234", usuario.getPassword());
        assertEquals(RolUsuario.PROFESIONAL, usuario.getRol());
        assertNull(usuario.getPacienteId());
        assertEquals(10L, usuario.getProfesionalId());
        assertTrue(usuario.getActivo());
    }
}
