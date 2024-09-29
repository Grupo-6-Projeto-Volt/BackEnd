package sptech.school.projetovolt.service.imagemproduto.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ImagemCriacaoDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String codigoImagem;

    @NotNull
    @PositiveOrZero
    private Integer indiceVt;

    @Min(1)
    @NotNull
    @Positive
    private Integer idProduto;
}
