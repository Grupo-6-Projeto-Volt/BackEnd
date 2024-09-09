package sptech.school.projetovolt.entity.vwprodutosmaisacessados;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VwProdutosMaisAcessados {
    @Column
    private int acessos;
    @Column
    private String nome;
    @Column
    private int qtd;
    @Id
    private Integer id;
    @Column
    private String url;
}
