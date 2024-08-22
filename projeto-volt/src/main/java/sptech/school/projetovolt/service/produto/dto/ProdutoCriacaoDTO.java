package sptech.school.projetovolt.service.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Produto Criacao DTO", description = "DTO para criação de um produto")
public class ProdutoCriacaoDTO {
    @Size(min = 3, max = 80)
    @NotNull
    @Schema(description = "Nome do produto", example = "Iphone 10 Pro Max")
    private String nome;

    @Size(max = 300)
    @NotNull
    @Schema(description = "Descrição do produto", example = "Configuração do Iphone 10 Pro Max é a mais avançada do mercado")
    private String descricao;

    @Size(max = 100)
    @NotNull
    @Schema(description = "Categoria do produto", example = "Eletrônicos")
    private String categoria;

    @PositiveOrZero
    @Schema(description = "Preço do produto", example = "10000.00")
    private Double preco;

    @PositiveOrZero
    @Schema(description = "Quantidade em estoque do produto", example = "10")
    private Integer qtdEstoque;

    @NotNull
    @Size(max = 45)
    @Schema(description = "Estado geral do produto", example = "Novo")
    private String estadoGeral;

    @PositiveOrZero
    @Schema(description = "Desconto do produto (Ex: 10 = 10%)", example = "10")
    private Integer desconto;

    @FutureOrPresent
    @Schema(description = "Data de inicio do desconto")
    private LocalDate dataInicioDesconto;

    @FutureOrPresent
    @Schema(description = "Data de fim do desconto")
    private LocalDate dataFimDesconto;
}
