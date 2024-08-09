package sptech.school.projetovolt.service.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import sptech.school.projetovolt.api.configuration.security.jwt.GerenciadorTokenJwt;
import sptech.school.projetovolt.entity.exception.ConflictException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioLoginDto;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioTokenDto;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Ao utilizar a Login Service")
@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    private LoginService service;

    @Mock
    private LoginRepository repository;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @Nested
    @DisplayName("Método Autenticar")
    class Autenticar {

        @Test
        @DisplayName("Autenticar com credenciais válidas")
        void autenticarComCredenciaisValidas() {
            // GIVEN
            UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto();
            usuarioLoginDto.setEmail("email@test.com");
            usuarioLoginDto.setSenha("senha");

            UsernamePasswordAuthenticationToken credentials =
                    new UsernamePasswordAuthenticationToken(usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());
            Authentication authentication = mock(Authentication.class);
            Login usuarioAutenticado = new Login();
            usuarioAutenticado.setEmail(usuarioLoginDto.getEmail());
            String token = "token-jwt";

            // WHEN
            when(authenticationManager.authenticate(credentials)).thenReturn(authentication);
            when(repository.findByEmail(usuarioLoginDto.getEmail())).thenReturn(Optional.of(usuarioAutenticado));
            when(gerenciadorTokenJwt.generateToken(authentication)).thenReturn(token);

            // THEN
            UsuarioTokenDto resposta = service.autenticar(usuarioLoginDto);

            // ASSERT
            assertNotNull(resposta);
            assertEquals(token, resposta.getToken());
            assertEquals(usuarioAutenticado.getEmail(), resposta.getEmail());
            verify(authenticationManager, times(1)).authenticate(credentials);
            verify(repository, times(1)).findByEmail(usuarioLoginDto.getEmail());
            verify(gerenciadorTokenJwt, times(1)).generateToken(authentication);
        }
    }

    @Nested
    @DisplayName("Método Criar Login")
    class CriarLogin {

        @Test
        @DisplayName("Criar login com email já existente")
        void criarLoginEmailExistente() {
            // GIVEN
            Usuario usuario = new Usuario();
            usuario.setEmail("email@test.com");
            String senha = "senha";

            // WHEN
            when(repository.existsByEmail(usuario.getEmail())).thenReturn(true);

            // THEN
            ConflictException exception = assertThrows(ConflictException.class, () -> service.criarLogin(usuario, senha));

            // ASSERT
            // TODO: Mensagem de erro com um espaço a mais
            assertEquals("Erro 409: Login  com valor duplicado!", exception.getMessage());
            verify(repository, times(1)).existsByEmail(usuario.getEmail());
        }
    }

    @Nested
    @DisplayName("Método Listar Logins")
    class ListarLogins {

        @Test
        @DisplayName("Listar todos os logins")
        void listarTodosLogins() {
            // GIVEN
            Login login = new Login();
            login.setEmail("email@test.com");

            // WHEN
            when(repository.findAll()).thenReturn(List.of(login));

            // THEN
            List<Login> resposta = service.listarLogins();

            // ASSERT
            assertFalse(resposta.isEmpty());
            assertEquals(1, resposta.size());
            assertEquals(login.getEmail(), resposta.get(0).getEmail());
            verify(repository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Método Encontrar Login Por Id")
    class EncontrarLoginPorId {

        @Test
        @DisplayName("Encontrar login por ID existente")
        void encontrarLoginPorIdExistente() {
            // GIVEN
            String id = "1";
            Login login = new Login();
            login.setId(id);

            // WHEN
            when(repository.findById(id)).thenReturn(Optional.of(login));

            // THEN
            Login resposta = service.encontrarLoginPorId(id);

            // ASSERT
            assertNotNull(resposta);
            assertEquals(id, resposta.getId());
            verify(repository, times(1)).findById(id);
        }

        @Test
        @DisplayName("Encontrar login por ID inexistente")
        void encontrarLoginPorIdInexistente() {
            // GIVEN
            String id = "1";

            // WHEN
            when(repository.findById(id)).thenReturn(Optional.empty());

            // THEN
            NotFoundException exception = assertThrows(NotFoundException.class, () -> service.encontrarLoginPorId(id));

            // ASSERT
            assertEquals("Erro 404: Login %s não encontrado!".formatted(id), exception.getMessage());
            verify(repository, times(1)).findById(id);
        }
    }

    @Nested
    @DisplayName("Método Alterar Email")
    class AlterarEmail {

        @Test
        @DisplayName("Alterar email com dados válidos")
        void alterarEmailDadosValidos() {
            // GIVEN
            String id = "1";
            String novoEmail = "novoemail@test.com";
            Login login = new Login();
            login.setId(id);
            login.setEmail("email@test.com");

            // WHEN
            when(repository.findById(id)).thenReturn(Optional.of(login));
            when(repository.existsByEmail(novoEmail)).thenReturn(false);
            when(repository.save(login)).thenReturn(login);

            // THEN
            Login resposta = service.alterarEmail(id, novoEmail);

            // ASSERT
            assertNotNull(resposta);
            assertEquals(novoEmail, resposta.getEmail());
            verify(repository, times(1)).findById(id);
            verify(repository, times(1)).existsByEmail(novoEmail);
            verify(repository, times(1)).save(login);
        }

        @Test
        @DisplayName("Alterar e-mail já existente no banco")
        void alterarEmailExistente() {
            // GIVEN
            String id = "1";
            String novoEmail = "novoemail@test.com";

            // WHEN
            when(repository.existsByEmail(novoEmail)).thenReturn(true);

            // THEN
            ConflictException exception = assertThrows(ConflictException.class, () -> service.alterarEmail(id, novoEmail));

            // ASSERT
            assertEquals("Erro 409: Login %s com valor duplicado!".formatted(novoEmail), exception.getMessage());
            verify(repository, times(1)).existsByEmail(novoEmail);
        }
    }

    @Nested
    @DisplayName("Método Alterar Senha")
    class AlterarSenha {

        @Test
        @DisplayName("Alterar senha com dados válidos")
        void alterarSenhaDadosValidos() {
            // GIVEN
            String id = "1";
            String novaSenha = "novasenha";
            Login login = new Login();
            login.setId(id);
            login.setSenha("senha");

            // WHEN
            when(repository.findById(id)).thenReturn(Optional.of(login));
            when(repository.existsBySenha(novaSenha)).thenReturn(false);
            when(repository.save(login)).thenReturn(login);

            // THEN
            Login resposta = service.alterarSenha(id, novaSenha);

            // ASSERT
            assertNotNull(resposta);
            assertEquals(novaSenha, resposta.getSenha());
            verify(repository, times(1)).findById(id);
            verify(repository, times(1)).existsBySenha(novaSenha);
            verify(repository, times(1)).save(login);
        }

        @Test
        @DisplayName("Alterar senha com senha já existente")
        void alterarSenhaExistente() {
            // GIVEN
            String id = "1";
            String novaSenha = "novasenha";

            // WHEN
            when(repository.existsBySenha(novaSenha)).thenReturn(true);

            // THEN
            ConflictException exception = assertThrows(ConflictException.class, () -> service.alterarSenha(id, novaSenha));

            // ASSERT
            assertEquals("Erro 409: Login %s com valor duplicado!".formatted(novaSenha), exception.getMessage());
            verify(repository, times(1)).existsBySenha(novaSenha);
        }
    }
}
