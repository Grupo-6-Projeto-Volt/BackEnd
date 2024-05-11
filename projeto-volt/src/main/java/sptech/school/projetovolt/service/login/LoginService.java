package sptech.school.projetovolt.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.api.configuration.security.jwt.GerenciadorTokenJwt;
import sptech.school.projetovolt.entity.exception.ConflictException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.login.dto.LoginMapper;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioLoginDto;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioTokenDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new
                UsernamePasswordAuthenticationToken(usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Login usuarioAutenticado = loginRepository.findByEmail(usuarioLoginDto.getEmail())
                .orElseThrow(
                        () -> new NotFoundException("Login " + usuarioLoginDto.getEmail())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return LoginMapper.of(usuarioAutenticado, token);
    }

    public Login criarLogin(Usuario usuario, String senha) {
        if (loginRepository.existsByEmail(usuario.getEmail()) || loginRepository.existsBySenha(senha)) {
            throw new ConflictException("Login ");
        }
        Login login = new Login();
        login.setEmail(usuario.getEmail());
        login.setSenha(senha);
        login.setUsuario(usuario);
        return loginRepository.save(login);
    }

    public Login encontrarLoginPorId(String id) {
        return loginRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Login " + id));
    }

    public Login atualizarEmail(String id, String email) {
        if (loginRepository.existsByEmail(email)) {
            throw new ConflictException("Login " + id);
        }

        Login login = encontrarLoginPorId(id);
        login.setEmail(email);
        return loginRepository.save(login);

    }

}
