package sptech.school.projetovolt.entity.classificacaoProduto;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

@Data
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
