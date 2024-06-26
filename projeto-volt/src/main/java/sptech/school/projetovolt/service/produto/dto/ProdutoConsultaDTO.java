package sptech.school.projetovolt.service.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Produto Consulta DTO", description = "DTO para consulta de um produto")
public class ProdutoConsultaDTO {

    @Schema(description = "Nome do produto")
    private String nome;

    @Schema(description = "Descrição do produto")
    private String descricao;

    @Schema(description = "Categoria do produto")
    private String categoria;

    @Schema(description = "Preço do produto")
    private Double preco;

    @Schema(description = "Quantidade em estoque do produto")
    private Integer qtdEstoque;

    @Schema(description = "Estado geral do produto")
    private String estadoGeral;

    @Schema(description = "Desconto do produto (Ex: 10 = 10%)")
    private Integer desconto;
}
