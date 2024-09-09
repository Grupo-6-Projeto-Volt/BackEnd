package sptech.school.projetovolt.service.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import sptech.school.projetovolt.entity.corProduto.CorProduto;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(name = "Produto Consulta DTO", description = "DTO para consulta de um produto")
public class ProdutoConsultaDTO {

    @Schema(description = "ID do produto")
    private Integer id;

    @Schema(description = "Nome do produto")
    private String nome;

    @Schema(description = "Descrição do produto")
    private String descricao;

    @Schema(description = "Preço do produto")
    private Double preco;

    @Schema(description = "Quantidade em estoque do produto")
    private Integer qtdEstoque;

    @Schema(description = "Estado geral do produto")
    private String estadoGeral;

    @Schema(description = "Desconto do produto (Ex: 10 = 10%)")
    private Integer desconto;

    @Schema(description = "Data de inicio do desconto")
    private LocalDate dataInicioDesconto;

    @Schema(description = "Data de fim do desconto")
    private LocalDate dataFimDesconto;

    @Schema(description = "Categoria do produto")
    private String categoria;

    private List<ImagemProduto> imagensProduto;
    private List<TagProduto> tagsProduto;
    private List<CorProduto> coresProduto;

    @Data
    public static class ImagemProduto {
        private Integer id;
        private String nome;
        private String codigoImagem;
    }

    @Data
    public static class TagProduto {
        private Integer id;
        private String tag;
    }

    @Data
    public static class Categoria {
        private Integer id;
        private String nome;
    }

    @Data
    public static class CorProduto {
        private Integer id;
        private String nome;
        private String hexId;
    }
}
