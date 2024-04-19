package sptech.school.projetovolt.entity.favorito;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.usuario.Usuario;

@Getter
@Setter
@Entity
@Table(name="tb_favoritos")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @OneToMany(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
//    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
}
