package sptech.school.projetovolt.service.usuario.dto;

import lombok.Data;

@Data
public class UsuarioConsultaDto {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String categoria;
    private UsuarioLoginListagemDto login;
}
