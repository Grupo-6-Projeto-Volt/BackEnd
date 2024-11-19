package sptech.school.projetovolt.entity.vwprodutosmaisacessados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwprodutosmaisacessados.VwProdutosMaisAcessados;

import java.util.List;

public interface VwProdutosMaisAcessadosRepository extends JpaRepository<VwProdutosMaisAcessados,Integer> {
    @Query(value = """
                SELECT
                	vw.id,
                    vw.qtd,
                    vw.nome,
                    COUNT(vw.dataClick) as acessos,
                    vw.url
                FROM vwprodutosmaisacessados as vw
                where vw.dataClick = '2024-04-03'
                GROUP BY vw.id, vw.qtd, vw.nome
                ORDER BY vw.dataClick DESC
                LIMIT 7;
            """,nativeQuery = true)
    List<VwProdutosMaisAcessados> produtosMaisAcessados();

}
