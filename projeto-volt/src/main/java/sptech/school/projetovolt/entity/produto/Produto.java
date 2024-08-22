package sptech.school.projetovolt.entity.produto;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.classificacaoproduto.ClassificacaoProduto;
import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.entity.favoritos.Favoritos;
import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String categoria;

    @Column
    private Double preco;

    @Column(name = "qtd_estoque")
    private Integer qtdEstoque;

    @Column(name = "estado_geral")
    private String estadoGeral;

    @Column(name = "desconto")
    private Integer desconto;

    @Column(name = "data_inicio_desconto")
    private LocalDate dataInicioDesconto;

    @Column(name = "data_fim_desconto")
    private LocalDate dataFimDesconto;

    @ManyToMany
    @JoinTable(
            name = "tb_classificacao_produto",
            joinColumns = @JoinColumn(name = "fk_produto"),
            inverseJoinColumns = @JoinColumn(name = "fk_tag_produto"))
    private List<TagProduto> tags;

    @OneToMany(mappedBy = "produto")
    private List<Favoritos> favoritos;

    @OneToMany(mappedBy = "produto")
    private List<ClickProduto> clickProdutos;

    @OneToMany(mappedBy = "produto")
    private List<ClassificacaoProduto> classificacaoProdutos;

    @OneToMany(mappedBy = "produto")
    private List<ImagemProduto> imagensProduto;

}