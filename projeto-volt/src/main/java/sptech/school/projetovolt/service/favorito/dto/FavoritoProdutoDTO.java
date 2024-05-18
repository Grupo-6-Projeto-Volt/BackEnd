package sptech.school.projetovolt.service.favorito.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FavoritoProdutoDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private String categoria;
    private Double preco;
    private Integer qtdEstoque;
    private String estadoGeral;
    private Integer desconto;
}
