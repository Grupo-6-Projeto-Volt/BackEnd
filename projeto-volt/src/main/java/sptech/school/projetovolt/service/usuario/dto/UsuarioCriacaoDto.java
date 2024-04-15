package sptech.school.projetovolt.service.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
    @Size(min = 9, max = 9)
    @NotBlank
    private String cep;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
