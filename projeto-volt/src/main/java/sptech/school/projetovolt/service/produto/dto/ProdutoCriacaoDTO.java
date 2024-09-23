package sptech.school.projetovolt.service.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    @Positive
    @Schema(description = "Id da categoria do produto", example = "1")
    private Integer idCategoria;

    @NotNull
    @Schema(description = "Lista de imagens a serem cadastradas para o produto")
    private List<ProdutoConsultaDTO.ImagemProduto> imagensProduto;

    @NotNull
    @Schema(description = "Lista de tags a serem cadastradas para o produto")
    private List<ProdutoConsultaDTO.TagProduto> tagsProduto;

    @NotNull
    @Schema(description = "Lista de cores a serem cadastradas para o produto")
    private List<ProdutoConsultaDTO.CorProduto> coresProduto;

    @Data
    public static class ImagemProduto {
        private String nome;
        private String codigoImagem;
    }

    @Data
    public static class TagProduto {
        private String tag;
    }

    @Data
    public static class CorProduto {
        private String nome;
        private String hexId;
    }
}
