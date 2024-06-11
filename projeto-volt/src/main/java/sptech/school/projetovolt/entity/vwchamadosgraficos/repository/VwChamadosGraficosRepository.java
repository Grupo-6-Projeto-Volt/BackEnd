package sptech.school.projetovolt.entity.vwchamadosgraficos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwchamadosgraficos.VwChamadosGraficos;

import java.util.List;

public interface VwChamadosGraficosRepository extends JpaRepository<VwChamadosGraficos,Integer> {
    @Query(value = "SELECT * FROM vwchamadosgrafico AS vw WHERE dia IS NOT NULL AND mes IS NOT NULL ORDER BY mes DESC LIMIT 4",nativeQuery = true)
    List<VwChamadosGraficos> chamadosCanceladosConcluidos();
}
