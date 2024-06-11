package sptech.school.projetovolt.entity.vwchamadosgraficos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VwChamadosGraficos {
    @Column
    private int qtd;
    @Column
    private Integer dia;
    @Column
    private Integer mes;
    @Id
    private Integer id;
    @Column
    private int status;
}
