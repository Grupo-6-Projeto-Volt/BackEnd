package sptech.school.projetovolt.service.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginCriacaoDto {

    @Email
    @NotEmpty
    @Size(min = 10, max = 255)
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String senha;

    private Integer fkUsuario;
}
