package sptech.school.projetovolt.service.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginCriacaoDto {

    @Email
    @NotEmpty
    @Size(min = 10, max = 255)
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String senha;

    private Integer fkUsuario;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
