package sptech.school.projetovolt.entity.classificacaoproduto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

@Getter
@Setter
@Entity
@Table(name = "tb_classificacao_produto")
public class ClassificacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_tag_produto")
    private TagProduto tagProduto;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;
}
