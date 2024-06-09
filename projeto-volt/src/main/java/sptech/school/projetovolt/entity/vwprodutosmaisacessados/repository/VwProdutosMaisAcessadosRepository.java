package sptech.school.projetovolt.entity.vwprodutosmaisacessados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwprodutosmaisacessados.VwProdutosMaisAcessados;

import java.util.List;

public interface VwProdutosMaisAcessadosRepository extends JpaRepository<VwProdutosMaisAcessados,Integer> {
    @Query(value = "SELECT * FROM vwprodutosmaisacessados AS vw",nativeQuery = true)
    List<VwProdutosMaisAcessados> produtosMaisAcessados();

}
