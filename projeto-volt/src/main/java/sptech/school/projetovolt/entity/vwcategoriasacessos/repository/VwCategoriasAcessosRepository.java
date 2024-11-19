package sptech.school.projetovolt.entity.vwcategoriasacessos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwcategoriasacessos.VwCategoriasAcessos;

import java.time.LocalDate;
import java.util.List;

public interface VwCategoriasAcessosRepository extends JpaRepository<VwCategoriasAcessos,Integer> {
    @Query(value = """
                SELECT
                    vw.id,
                    COUNT(vw.dataClick) AS acessos,
                    vw.categoria
                FROM vwcategoriasacessos as vw
                WHERE vw.dataClick = :data
                GROUP BY vw.categoria, vw.id
                ORDER BY vw.acessos DESC;
            """, nativeQuery = true)
    List<VwCategoriasAcessos> categoriasMaisAcessadas(LocalDate data);
}
