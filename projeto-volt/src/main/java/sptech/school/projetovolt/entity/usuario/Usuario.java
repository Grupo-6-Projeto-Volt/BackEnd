package sptech.school.projetovolt.entity.usuario;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.entity.favoritos.Favoritos;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.produtoChamado.ProdutoChamado;

import java.util.List;

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
    private Short categoria;

    @OneToOne(mappedBy = "usuario")
    private Login login;

    @OneToMany(mappedBy = "usuario")
    private List<Favoritos> favoritos;

    @OneToMany(mappedBy = "usuario")
    private List<ClickProduto> clickProdutos;

    @OneToMany(mappedBy = "usuario")
    private List<ProdutoChamado> produtoChamados;

}
