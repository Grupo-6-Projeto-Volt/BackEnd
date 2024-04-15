package sptech.school.projetovolt.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.projetovolt.api.configuration.security.jwt.GerenciadorTokenJwt;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.service.login.dto.LoginMapper;
import sptech.school.projetovolt.service.login.dto.autenticacao.dto.UsuarioLoginDto;
import sptech.school.projetovolt.service.login.dto.autenticacao.dto.UsuarioTokenDto;

public class LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new
                UsernamePasswordAuthenticationToken(usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Login usuarioAutenticado = loginRepository.findByEmail(usuarioLoginDto.getEmail())
                .orElseThrow(
                        () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return LoginMapper.of(usuarioAutenticado, token);
    }

}
