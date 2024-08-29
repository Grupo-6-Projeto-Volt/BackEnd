package sptech.school.projetovolt.service.corproduto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CorProdutoCriacaoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String hexId;
}
