package sptech.school.projetovolt.api.usuario.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.usuario.UsuarioService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
//@Import({UsuarioController.class})
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

//    @Test
//    @DisplayName("Ao chamar listagem, retorna 200")
//    void listagemCorreta() throws Exception {
//
//        List<Usuario> usuarios = List.of(
//                new Usuario(1, "aaa", null, null, null, null, null, null, null),
//                new Usuario(2, "aaa", null, null, null, null, null, null, null)
//        );
//
//
//        when(usuarioService.listarUsuarios()).thenReturn(usuarios);
//
//        mockMvc.perform(get("/usuarios"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").hasJsonPath())
//                .andExpect(jsonPath("$[0].id").value("1"))
//                .andExpect(jsonPath("$[0].nome").value("aaa"))
//                .andExpect(jsonPath("$[1].id").value("2"));
//    }

}