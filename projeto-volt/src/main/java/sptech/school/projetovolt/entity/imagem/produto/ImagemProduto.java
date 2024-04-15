package sptech.school.projetovolt.entity.imagem.produto;

import jakarta.persistence.*;
import lombok.*;
import sptech.school.projetovolt.entity.produto.Produto;

@Data
@Entity
@Table(name = "tb_imagem_produto")
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fk_produto")
    @OneToMany(cascade = CascadeType.MERGE,
    orphanRemoval = true)
    private Produto produto;

    @Column
    private String nome;

    @Column(name = "codigo_imagem")
    private Byte[] codigoImagem;
}
