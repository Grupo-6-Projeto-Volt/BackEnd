package sptech.school.projetovolt.service.login.dto;

import lombok.Data;

@Data
public class BuscarLoginDto {
    private String id;
    private String email;
    private String senha;
    private LoginUsuarioListagemDto usuario;
}
