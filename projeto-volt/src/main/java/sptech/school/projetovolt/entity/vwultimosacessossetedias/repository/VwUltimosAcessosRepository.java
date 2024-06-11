package sptech.school.projetovolt.entity.vwultimosacessossetedias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwultimosacessossetedias.VwUltimosAcessosSeteDias;

import java.util.List;

public interface VwUltimosAcessosRepository extends JpaRepository<VwUltimosAcessosSeteDias,Integer> {

    @Query(value = "SELECT * FROM vwacessosultimossetedias AS vw",nativeQuery = true)
    List<VwUltimosAcessosSeteDias> ultimosAcessosNosSeteDias();
}
