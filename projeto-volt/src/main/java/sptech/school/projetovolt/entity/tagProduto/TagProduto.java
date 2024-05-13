package sptech.school.projetovolt.entity.tagProduto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.classificacaoProduto.ClassificacaoProduto;
import sptech.school.projetovolt.entity.produto.Produto;

import java.util.List;

@Data
@Entity
@Table(name = "tb_tag_produto")
@Schema(name = "Tag Produto", description = "Entidade que representa uma tag de um produto")
public class TagProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificação da tag", example = "1")
    private Integer id;

    @Column(name = "tag")
    @Schema(description = "Nome da tag", example = "Promoção")
    private String tag;

    @OneToMany(mappedBy = "tagProduto")
    private List<ClassificacaoProduto> classificacaoProdutos;
}
