package sptech.school.projetovolt.entity.corProduto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.produto.Produto;

@Getter
@Setter
@Entity
@Table(name = "tb_cor_produto")
public class CorProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "hex_id")
    private String hexId;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

}
