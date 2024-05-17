package sptech.school.projetovolt.entity.favoritos;

import jakarta.persistence.*;
<<<<<<< HEAD:projeto-volt/src/main/java/sptech/school/projetovolt/entity/favoritos/Favorito.java
import lombok.Data;
import sptech.school.projetovolt.entity.listaFavoritos.ListaFavorita;
=======

import lombok.Getter;
import lombok.Setter;
import sptech.school.projetovolt.entity.produto.Produto;
>>>>>>> 9cb0cb033d3efb9d3a9d1e9e56dcee43d9072ee3:projeto-volt/src/main/java/sptech/school/projetovolt/entity/favoritos/Favoritos.java
import sptech.school.projetovolt.entity.usuario.Usuario;

import java.time.LocalDate;

@Getter
@Setter
@Entity
<<<<<<< HEAD:projeto-volt/src/main/java/sptech/school/projetovolt/entity/favoritos/Favorito.java
@Table(name="tb_favoritos")
public class Favorito {
=======
@Table(name = "tb_lista_favoritos")
public class Favoritos {

>>>>>>> 9cb0cb033d3efb9d3a9d1e9e56dcee43d9072ee3:projeto-volt/src/main/java/sptech/school/projetovolt/entity/favoritos/Favoritos.java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dt_hora_insercao")
    private LocalDate dtHoraInsercao;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;


}
