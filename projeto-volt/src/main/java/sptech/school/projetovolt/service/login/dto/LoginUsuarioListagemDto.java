package sptech.school.projetovolt.service.login.dto;

import lombok.Data;

@Data
public class LoginUsuarioListagemDto {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String categoria;
}
