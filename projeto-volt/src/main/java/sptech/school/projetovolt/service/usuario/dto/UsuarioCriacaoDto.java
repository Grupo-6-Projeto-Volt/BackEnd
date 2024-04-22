package sptech.school.projetovolt.service.usuario.dto;

import jakarta.validation.constraints.*;

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

    public Short getCategoria() {
        return categoria;
    }

    public void setCategoria(Short categoria) {
        this.categoria = categoria;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
