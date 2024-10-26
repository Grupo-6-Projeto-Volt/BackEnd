package sptech.school.projetovolt.service.corproduto.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CorProdutoCriacaoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 7, max = 7)
    private String hexId;

    @NotNull
    @Positive
    private Integer idProduto;

}
