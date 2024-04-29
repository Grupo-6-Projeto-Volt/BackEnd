package sptech.school.projetovolt.service.tagProduto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Tag Produto Criacao DTO", description = "DTO para criação de uma tag de produto")
public class TagProdutoCriacaoDto {
    @NotNull
    @Size(min = 3, max = 50)
    @Schema(description = "Nome da tag", example = "Promoção")
    private String tag;
}
