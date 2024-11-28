package sptech.school.projetovolt.entity.vwprodutosmaisacessados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwprodutosmaisacessados.VwProdutosMaisAcessados;

import java.time.LocalDate;
import java.util.List;

public interface VwProdutosMaisAcessadosRepository extends JpaRepository<VwProdutosMaisAcessados,Integer> {
    @Query(value = """
                SELECT
                	vw.id,
                    vw.qtd,
                    vw.nome,
                    COUNT(vw.id) as acessos,
                    vw.url
                FROM vwprodutosmaisacessados as vw
                WHERE vw.dataClick BETWEEN :dataInicio AND :dataFim
                GROUP BY vw.id
                ORDER BY acessos DESC
                LIMIT 7;
            """,nativeQuery = true)
    List<VwProdutosMaisAcessados> produtosMaisAcessados(LocalDate dataInicio, LocalDate dataFim);

}
