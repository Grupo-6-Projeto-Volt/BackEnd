package sptech.school.projetovolt.service.imagemproduto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ImagemCriacaoDto {

    @NotBlank
    private String nome;

    @NotBlank
    private byte[] codigoImagem;

    @Min(1)
    @NotNull
    @Positive
    private Integer idProduto;
}
