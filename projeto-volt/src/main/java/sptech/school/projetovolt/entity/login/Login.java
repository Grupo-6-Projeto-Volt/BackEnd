package sptech.school.projetovolt.entity.login;

import jakarta.persistence.*;
import sptech.school.projetovolt.entity.usuario.Usuario;

import java.util.UUID;

@Entity
@Table(name = "tb_login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
