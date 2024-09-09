package sptech.school.projetovolt.entity.vwultimosacessossetedias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class VwUltimosAcessosSeteDias {
    @Column
    private int qtd;
    @Id
    private Integer id;
}
