package sptech.school.projetovolt.service.login.dto.autenticacao.dto;

import java.util.UUID;

public class UsuarioTokenDto {

    private UUID userId;
    private String email;
    private String token;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
