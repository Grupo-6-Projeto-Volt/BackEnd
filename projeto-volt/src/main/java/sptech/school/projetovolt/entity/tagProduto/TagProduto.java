package sptech.school.projetovolt.entity.tagProduto;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.produto.Produto;

import java.util.List;

@Data
@Entity
@Table(name = "tb_tag_produto")
public class TagProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tag")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    List<Produto> produtos;
}
