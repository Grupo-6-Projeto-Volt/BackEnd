package sptech.school.projetovolt.service.login.dto;

import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioTokenDto;

import java.util.List;

public class LoginMapper {

    public static UsuarioTokenDto of(Login login, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();
        usuarioTokenDto.setUserId(login.getId());
        usuarioTokenDto.setEmail(login.getEmail());
        usuarioTokenDto.setToken(token);
        return usuarioTokenDto;
    }

    public static Login toLogin (LoginCriacaoDto dto, Usuario usuario) {
        Login login = new Login();
        login.setEmail(dto.getEmail());
        login.setSenha(dto.getSenha());
        login.setUsuario(usuario);
        return login;
    }

    public static LoginCriacaoDto toCadastrarLoginDto (Login entity) {
        LoginCriacaoDto loginCriacaoDto = new LoginCriacaoDto();
        loginCriacaoDto.setEmail(entity.getEmail());
        loginCriacaoDto.setSenha(entity.getSenha());
        loginCriacaoDto.setFkUsuario(entity.getUsuario().getId());
        return loginCriacaoDto;
    }

    public static BuscarLoginDto toBuscarLoginDto (Login entity) {
        BuscarLoginDto buscarLoginDto = new BuscarLoginDto();
        buscarLoginDto.setId(entity.getId());
        buscarLoginDto.setEmail(entity.getEmail());
        buscarLoginDto.setSenha(entity.getSenha());

        LoginUsuarioListagemDto loginUsuarioListagemDto = new LoginUsuarioListagemDto();
        loginUsuarioListagemDto.setId(entity.getUsuario().getId());
        loginUsuarioListagemDto.setNome(entity.getUsuario().getNome());
        loginUsuarioListagemDto.setTelefone(entity.getUsuario().getTelefone());
        loginUsuarioListagemDto.setEmail(entity.getUsuario().getEmail());

        if (entity.getUsuario().getCategoria() == 0) {
            loginUsuarioListagemDto.setCategoria("Admin");
        } else {
            loginUsuarioListagemDto.setCategoria("Cliente");
        }

        buscarLoginDto.setUsuario(loginUsuarioListagemDto);
        return buscarLoginDto;
    }

    public static List<BuscarLoginDto> toBuscarLoginDto (List<Login> entity) {
        return entity.stream().map(LoginMapper::toBuscarLoginDto).toList();
    }

}
