package sptech.school.projetovolt.entity.login;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.usuario.Usuario;


@Getter
@Setter
@Entity
@Table(name = "tb_login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
  
    @Column
    private String email;
    
    @Column
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
