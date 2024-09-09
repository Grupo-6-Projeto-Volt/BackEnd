package sptech.school.projetovolt.service.classificacaoproduto.dto;

import lombok.Data;

@Data
public class ClassificacaoProdutoConsultaDto {

    private Integer id;
    private Produto produto;
    private TagProduto tag;

    @Data
    public static class Produto {
        private Integer id;
        private String nome;
        private String descricao;
        private String categoria;
        private Double preco;
        private Integer qtdEstoque;
        private String estadoGeral;
        private Integer desconto;
    }

    @Data
    public static class TagProduto {
        private Integer id;
        private String tag;
    }

}
