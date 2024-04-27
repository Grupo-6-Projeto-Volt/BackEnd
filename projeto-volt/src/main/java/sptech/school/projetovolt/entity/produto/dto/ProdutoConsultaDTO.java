package sptech.school.projetovolt.entity.produto.dto;

import lombok.Data;

@Data
public class ProdutoConsultaDTO {
    private String nome;
    private String descricao;
    private String categoria;
    private Double preco;
    private Integer qtdEstoque;
    private String estadoGeral;
    private Integer desconto;
}
