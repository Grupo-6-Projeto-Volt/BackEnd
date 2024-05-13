package sptech.school.projetovolt.entity.favoritos;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.usuario.Usuario;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_lista_favoritos")
public class Favoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dt_hora_insercao")
    private LocalDate dtHoraInsercao;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;


}
