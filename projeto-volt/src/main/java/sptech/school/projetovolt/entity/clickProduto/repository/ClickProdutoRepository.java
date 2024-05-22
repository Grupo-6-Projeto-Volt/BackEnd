package sptech.school.projetovolt.entity.clickProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoMaisClicadosDTO;

import java.util.List;

public interface ClickProdutoRepository extends JpaRepository<ClickProduto, Integer> {
    @Query(value = "SELECT count(c.fk_produto) AS \"qtdClicks\" ,p.nome AS \"nomeProduto\" FROM tb_click_produto AS c JOIN tb_produto AS p ON p.id=c.fk_produto GROUP BY c.fk_produto ORDER BY qtdClicks DESC;"
    , nativeQuery = true)
    List<ClickProdutoMaisClicadosDTO> produtosMaisClicados();
}
