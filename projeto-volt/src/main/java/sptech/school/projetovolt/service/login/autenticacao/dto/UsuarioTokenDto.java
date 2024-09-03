package sptech.school.projetovolt.service.login.autenticacao.dto;


import lombok.Data;

@Data
public class UsuarioTokenDto {
    private String userId;
    private Integer idUsuario;
    private String nome;
    private String email;
    private int categoria;
    private String token;
}
