package sptech.school.projetovolt.service.login.autenticacao.dto;


import lombok.Data;

@Data
public class UsuarioTokenDto {
    private String userId;
    private String email;
    private String token;
}
