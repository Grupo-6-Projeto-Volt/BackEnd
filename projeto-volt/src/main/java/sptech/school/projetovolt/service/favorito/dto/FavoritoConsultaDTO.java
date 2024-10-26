package sptech.school.projetovolt.service.favorito.dto;

import lombok.Data;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;

import java.time.LocalDateTime;

@Data
public class FavoritoConsultaDTO {
    private Integer id;
    private UsuarioConsultaDto usuario;
    private ProdutoConsultaDTO produto;
    private LocalDateTime dtHoraInsercao;
}
