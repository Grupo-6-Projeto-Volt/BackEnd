package sptech.school.projetovolt.service.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaCriacaoDTO {

    @Size(min = 3, max = 30)
    @NotBlank
    private String nome;

}
