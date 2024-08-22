package sptech.school.projetovolt.api.usuario.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import sptech.school.projetovolt.service.usuario.UsuarioService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Teste da UsuarioController")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Ao chamar listagem, retorna 204")
    void listagemIncorretaCorreta() throws Exception {
        when(usuarioService.listarUsuarios()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotHaveJsonPath());

    }
}