package sptech.school.projetovolt.entity.vwtaxaretorno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VwTaxaRetorno {
    @Id
    private Integer id;
    @Column
    private String usuario;
    @Column
    private int cliques;
}
