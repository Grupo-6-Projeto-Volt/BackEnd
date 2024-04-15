package sptech.school.projetovolt.entity.favorito;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.usuario.Usuario;

@Data
@Entity
@Table(name="tb_favoritos")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
