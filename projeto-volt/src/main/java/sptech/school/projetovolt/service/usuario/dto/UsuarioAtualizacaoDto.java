package sptech.school.projetovolt.service.usuario.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioAtualizacaoDto {

    @Size(min = 3, max = 100)
    @NotBlank
    private String nome;

    @Size(min = 3, max = 255)
    @NotBlank
    private String email;

    @Size(min = 10, max = 14)
    @NotBlank
    private String telefone;

    @Min(0)
    @Max(1)
    private Short categoria;

}
