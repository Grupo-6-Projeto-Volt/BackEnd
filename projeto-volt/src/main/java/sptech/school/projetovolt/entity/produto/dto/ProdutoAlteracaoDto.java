package sptech.school.projetovolt.entity.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Produto Alteracao DTO", description = "DTO para alteração de um produto")
public class ProdutoAlteracaoDto {
    @Size(min = 3, max = 80)
    @Schema(description = "Nome do produto", example = "Iphone 12 Pro Max")
    private String nome;

    @Size(max = 300)
    @Schema(description = "Descrição do produto", example = "O Iphone 12 Pro Max é o mais novo lançamento da Apple")
    private String descricao;

    @Size(max = 100)
    @Schema(description = "Categoria do produto", example = "Eletrônicos")
    private String categoria;

    @PositiveOrZero
    @Schema(description = "Preço do produto", example = "10000.00")
    private Double preco;

    @PositiveOrZero
    @Schema(description = "Quantidade em estoque do produto", example = "10")
    private Integer qtdEstoque;

    @Size(max = 45)
    @Schema(description = "Estado geral do produto", example = "Novo")
    private String estadoGeral;

    @PositiveOrZero
    @Schema(description = "Desconto do produto (10 = 10%)", example = "10")
    private Integer desconto;
}
