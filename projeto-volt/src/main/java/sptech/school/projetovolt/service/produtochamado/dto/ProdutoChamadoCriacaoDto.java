package sptech.school.projetovolt.service.produtochamado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Produto Chamado Criação DTO", description = "DTO para criação de um chamado de produto")
public class ProdutoChamadoCriacaoDto {

    @PositiveOrZero
    @NotNull
    @Schema(description = "Produto relacionado ao chamado")
    private Integer produtoId;

    @PositiveOrZero
    @NotNull
    @Schema(description = "Usuário relacionado ao chamado")
    private Integer usuarioId;

}
