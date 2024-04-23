package sptech.school.projetovolt.service.usuario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioCriacaoDto {

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

    @NotEmpty
    @Size(min = 8, max = 16)
    private String senha;
}
