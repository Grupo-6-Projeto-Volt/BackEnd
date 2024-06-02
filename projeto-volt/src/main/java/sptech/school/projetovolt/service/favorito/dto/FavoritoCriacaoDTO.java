package sptech.school.projetovolt.service.favorito.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class FavoritoCriacaoDTO {
    private Integer idUsuario;
    private Integer idProduto;
    private LocalDateTime dtHoraInsercao = LocalDateTime.now();
}
