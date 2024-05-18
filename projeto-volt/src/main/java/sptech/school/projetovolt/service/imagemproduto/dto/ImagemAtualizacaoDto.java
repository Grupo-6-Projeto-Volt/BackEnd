package sptech.school.projetovolt.service.imagemproduto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ImagemAtualizacaoDto {

    @NotBlank
    private String nome;

    @NotBlank
    private byte[] codigoImagem;
}
