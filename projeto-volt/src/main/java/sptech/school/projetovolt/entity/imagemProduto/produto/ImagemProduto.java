package sptech.school.projetovolt.entity.imagemProduto.produto;

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

    @Column
    private String nome;

    @Column(name = "codigo_imagem")
    private byte[] codigoImagem;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;
}
