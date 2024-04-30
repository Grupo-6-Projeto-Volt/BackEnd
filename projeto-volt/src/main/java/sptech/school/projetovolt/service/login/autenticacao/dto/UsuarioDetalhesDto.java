package sptech.school.projetovolt.service.login.autenticacao.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sptech.school.projetovolt.entity.login.Login;

import java.util.Collection;

public class UsuarioDetalhesDto implements UserDetails {

    private final String email;
    private final String senha;

    public UsuarioDetalhesDto(Login login) {
        this.email = login.getEmail();
        this.senha = login.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
