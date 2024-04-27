package sptech.school.projetovolt.entity.produto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoCriacaoDTO {
    @Size(min = 3, max = 80)
    @NotNull
    private String nome;
    @Size(max = 300)
    @NotNull
    private String descricao;
    @Size(max = 100)
    @NotNull
    private String categoria;
    @PositiveOrZero
    private Double preco;
    @PositiveOrZero
    private Integer qtdEstoque;
    @NotNull
    @Size(max = 45)
    private String estadoGeral;
    @PositiveOrZero
    private Integer desconto;
}
