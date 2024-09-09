package sptech.school.projetovolt.service.tagProduto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Tag Produto Consulta DTO", description = "DTO para consulta de uma tag de produto")
public class TagProdutoConsultaDto {

    private Integer id;

    @Schema(description = "Nome da tag", example = "Promoção")
    private String tag;
}
