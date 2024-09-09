package sptech.school.projetovolt.entity.vwtaxaretorno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwtaxaretorno.VwTaxaRetorno;

import java.util.List;

public interface VwTaxaRetornoRepository extends JpaRepository<VwTaxaRetorno,Integer> {
    @Query(value = "SELECT * FROM vwtaxaretorno AS vw ORDER BY cliques DESC",nativeQuery = true)
    List<VwTaxaRetorno> taxaDeRetorno();
}
