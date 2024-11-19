package sptech.school.projetovolt.entity.vwtaxaretorno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwtaxaretorno.VwTaxaRetorno;

import java.time.LocalDate;
import java.util.List;

public interface VwTaxaRetornoRepository extends JpaRepository<VwTaxaRetorno,Integer> {
    @Query(value = """
                SELECT
                    vw.id,
                    vw.usuario,
                    COUNT(vw.dataClick) AS cliques
                FROM vwtaxaretorno AS vw
                WHERE vw.dataClick = :data
                GROUP BY vw.id HAVING cliques > 1
                ORDER BY cliques DESC;
            """,nativeQuery = true)
    List<VwTaxaRetorno> taxaDeRetorno(LocalDate data);
}
