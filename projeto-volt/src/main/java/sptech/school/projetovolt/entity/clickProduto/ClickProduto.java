package sptech.school.projetovolt.entity.clickProduto;

import jakarta.persistence.*;
import lombok.Data;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.usuario.Usuario;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_click_produto")
public class ClickProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_hora_click")
    private LocalDate dataHoraClick;

    @Column(name = "possivel_compra")
    private Short possivelCompra;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;
}
