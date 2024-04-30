package sptech.school.projetovolt.service.login.autenticacao;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> loginOpt = loginRepository.findByEmail(username);

        if (loginOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuário %s não encontrado".formatted(username));
        }
        return new UsuarioDetalhesDto(loginOpt.get());
    }
}
