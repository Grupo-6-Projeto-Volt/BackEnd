package sptech.school.projetovolt.entity.listaFavoritos;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.favoritos.Favorito;
import sptech.school.projetovolt.entity.produto.Produto;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_lista_favoritos")
public class ListaFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dt_hora_insercao")
    private LocalDateTime dtHoraInsercao = LocalDateTime.now();

    @ManyToOne
//    @JoinColumn(name = "fk_favoritos")
    private Favorito favoritos;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

}
