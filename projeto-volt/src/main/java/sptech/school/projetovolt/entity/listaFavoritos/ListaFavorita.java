package sptech.school.projetovolt.entity.listaFavoritos;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.favoritos.Favoritos;
import sptech.school.projetovolt.entity.produto.Produto;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_lista_favoritos")
public class ListaFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dt_hora_insercao")
    private LocalDate dtHoraInsercao;

    @ManyToOne
    @JoinColumn(name = "fk_favoritos")
    private Favoritos favoritos;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

}
