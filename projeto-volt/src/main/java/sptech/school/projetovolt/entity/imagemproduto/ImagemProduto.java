package sptech.school.projetovolt.entity.imagemproduto;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.produto.Produto;

@Getter
@Setter
@Entity
@Table(name = "tb_imagem_produto")
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column(name = "codigo_imagem")
    private String codigoImagem;

    @Column(name = "indice_vt")
    private Integer indiceVt;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;
}
