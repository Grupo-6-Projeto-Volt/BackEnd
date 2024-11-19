package sptech.school.projetovolt.entity.vwultimosacessossetedias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwultimosacessossetedias.VwUltimosAcessosSeteDias;

import java.util.List;

public interface VwUltimosAcessosRepository extends JpaRepository<VwUltimosAcessosSeteDias,Integer> {

    @Query(value = """
                SELECT
                    COUNT(vw.dataClick) AS qtd,
                    vw.id
                FROM vwacessossetedias as vw
                WHERE vw.dataClick BETWEEN DATE_SUB('2024-05-10', INTERVAL 7 DAY) AND '2024-05-10'
                GROUP BY vw.id;;
            """,nativeQuery = true)
    List<VwUltimosAcessosSeteDias> ultimosAcessosNosSeteDias();
}
