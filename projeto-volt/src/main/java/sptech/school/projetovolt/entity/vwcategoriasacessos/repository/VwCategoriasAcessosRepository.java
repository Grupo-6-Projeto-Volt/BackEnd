package sptech.school.projetovolt.entity.vwcategoriasacessos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.vwcategoriasacessos.VwCategoriasAcessos;

import java.util.List;

public interface VwCategoriasAcessosRepository extends JpaRepository<VwCategoriasAcessos,Integer> {
    @Query(value = "SELECT * FROM vwcategoriasacessos AS vw ", nativeQuery = true)
    List<VwCategoriasAcessos> categoriasMaisAcessadas();
}
