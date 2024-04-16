package sptech.school.projetovolt.entity.produto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoAlteracaoDto {
    @Size(min = 3, max = 80)
    private String nome;
    @Size(max = 300)
    private String descricao;
    @Size(max = 100)
    private String categoria;
    @PositiveOrZero
    private Double preco;
    @PositiveOrZero
    private Integer qtdEstoque;
    @Size(max = 45)
    private String estadoGeral;
}
