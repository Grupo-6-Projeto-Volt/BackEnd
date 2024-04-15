package sptech.school.projetovolt.entity.produto;

import jakarta.persistence.*;
import lombok.*;

@Data
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
}