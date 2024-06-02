package sptech.school.projetovolt.entity.vwmaisclicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwmaisclicados.VwMaisClicados;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoMaisClicadosDTO;

import java.util.List;

public interface VwMaisClicadosRepository extends JpaRepository<VwMaisClicados, Integer> {
    @Query(value = "SELECT * FROM vwSelectMaisClicados AS vw ORDER BY vw.qtd_clicks DESC"
            , nativeQuery = true)
    List<VwMaisClicados> produtosMaisClicados();
}
