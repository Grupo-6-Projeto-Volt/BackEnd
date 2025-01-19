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
    @Id
    private Integer id;
    @Column
    private Integer acessos;
    @Column
    private String nome;
    @Column
    private Integer qtd;
    @Column
    private String url;
}
