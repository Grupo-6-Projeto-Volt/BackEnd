package sptech.school.projetovolt.entity.produtoChamado;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.usuario.Usuario;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_produto_chamado")
public class ProdutoChamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status_chamado")
    private Short statusChamado;

    @Column(name = "data_hora_abertura")
    private LocalDate dataHoraAbertura;

    @Column(name = "data_hora_fechamento")
    private LocalDate dataHoraFechamento;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

}
