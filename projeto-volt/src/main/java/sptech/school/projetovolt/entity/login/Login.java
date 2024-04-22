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
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
