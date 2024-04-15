package sptech.school.projetovolt.service.login.dto.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.service.login.dto.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> loginOpt = loginRepository.findByEmail(username);

        if (loginOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuário %s não encontrado".formatted(username));
        }
        return new UsuarioDetalhesDto(loginOpt.get());
    }
}
