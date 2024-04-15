package sptech.school.projetovolt.entity.usuario;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.login.Login;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String telefone;
    @Column
    private String cep;
    @Column
    private Short categoria;
    @OneToOne(mappedBy = "usuario")
    private Login login;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
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
//    public String getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(String telefone) {
//        this.telefone = telefone;
//    }
//
//    public String getCep() {
//        return cep;
//    }
//
//    public void setCep(String cep) {
//        this.cep = cep;
//    }
//
//    public Short getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(Short categoria) {
//        this.categoria = categoria;
//    }
}
