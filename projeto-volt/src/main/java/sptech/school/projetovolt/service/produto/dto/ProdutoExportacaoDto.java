package sptech.school.projetovolt.service.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Produto Exportação DTO", description = "DTO para exportação de um produto")
public class ProdutoExportacaoDto {

    @Schema(description = "ID do produto")
    private Integer id;

    @Schema(description = "Nome do produto")
    private String nome;

    @Schema(description = "Estado geral do produto")
    private String estadoGeral;

    @Schema(description = "Preço do produto")
    private Double preco;

    @Schema(description = "Categoria do produto")
    private String categoria;
}
