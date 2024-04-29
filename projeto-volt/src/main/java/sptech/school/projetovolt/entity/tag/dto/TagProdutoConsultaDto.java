package sptech.school.projetovolt.entity.tag.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Tag Produto Consulta DTO", description = "DTO para consulta de uma tag de produto")
public class TagProdutoConsultaDto {
    @Schema(description = "Nome da tag", example = "Promoção")
    private String tag;
}
