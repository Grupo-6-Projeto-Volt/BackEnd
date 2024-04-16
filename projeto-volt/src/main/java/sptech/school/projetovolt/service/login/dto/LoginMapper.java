package sptech.school.projetovolt.service.login.dto;

import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioTokenDto;

public class LoginMapper {

    public static UsuarioTokenDto of(Login login, String token) {

        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();
        usuarioTokenDto.setUserId(login.getId());
        usuarioTokenDto.setEmail(login.getEmail());
        usuarioTokenDto.setToken(token);
        return usuarioTokenDto;

    }

}
