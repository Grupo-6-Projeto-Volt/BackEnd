package sptech.school.projetovolt.entity.vwcategoriasacessos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VwCategoriasAcessos {
    @Id
    private Integer id;
    @Column
    private int acesssos;
    @Column
    private String categoria;
}
