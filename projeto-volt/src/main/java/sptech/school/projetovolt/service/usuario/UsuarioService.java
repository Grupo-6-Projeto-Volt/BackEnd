package sptech.school.projetovolt.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.usuario.repository.UsuarioRepository;
import sptech.school.projetovolt.service.login.LoginService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;

    public Usuario criarConta(Usuario novoUsuario, String senha) {
        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        Login loginSalvo = loginService.criarLogin(novoUsuario, senha);
        usuarioSalvo.setLogin(loginSalvo);
        return usuarioSalvo;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario " + id));
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuario) {
        buscarUsuarioPorId(id);
        usuario.setId(id);

        Login loginAlterado = loginService.alterarEmail(usuario.getLogin().getId(), usuario.getEmail());
        usuario.setLogin(loginAlterado);

        return usuarioRepository.save(usuario);
    }

    public Usuario alterarEmail(Integer id, String email) {
        Usuario usuarioEncontrado = buscarUsuarioPorId(id);
        usuarioEncontrado.setEmail(email);
        return usuarioRepository.save(usuarioEncontrado);
    }

}
