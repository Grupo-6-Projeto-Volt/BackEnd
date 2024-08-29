package sptech.school.projetovolt.service.imagemproduto.dto;

import lombok.Data;

@Data
public class ImagemConsultaDto {
    private Integer id;
    private String nome;
    private String codigoImagem;
    private Integer indiceVt;
    private Produto produto;

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

}
