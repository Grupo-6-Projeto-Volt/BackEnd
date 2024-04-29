package sptech.school.projetovolt.entity.favoritos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.usuario.Usuario;

@Data
@Entity
@Table(name="tb_favoritos")
public class Favoritos {
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
