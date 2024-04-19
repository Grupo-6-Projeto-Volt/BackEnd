package sptech.school.projetovolt.entity.login;

import jakarta.persistence.*;
import sptech.school.projetovolt.entity.usuario.Usuario;
import lombok.*;

import java.util.UUID;
@Data
@Entity
@Table(name = "tb_login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String email;
    @Column
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_login", referencedColumnName = "id")
    private Usuario usuario;

//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(String senha) {
//        this.senha = senha;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
}