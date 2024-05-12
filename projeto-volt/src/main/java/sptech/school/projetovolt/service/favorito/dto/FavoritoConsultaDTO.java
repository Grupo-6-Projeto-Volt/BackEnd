package sptech.school.projetovolt.service.favorito.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FavoritoConsultaDTO {
    private UsuarioDto usuario;
    private List<ListaFavoritoDTO> favoritos;
    @Data
    public static class UsuarioDto{
        private Integer id;
        private String nome;
        private String email;
        private String telefone;
    }

    @Data
    public static class ListaFavoritoDTO{
        private Integer id;
        private LocalDateTime dtHoraInsercao;
    }
}
