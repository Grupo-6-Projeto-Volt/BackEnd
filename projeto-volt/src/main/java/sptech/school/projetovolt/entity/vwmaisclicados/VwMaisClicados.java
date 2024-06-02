package sptech.school.projetovolt.entity.vwmaisclicados;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VwMaisClicados {
    @Id
    private Integer id;
    @Column
    private Integer qtdClicks;
    @Column
    private String nomeProduto;
}
