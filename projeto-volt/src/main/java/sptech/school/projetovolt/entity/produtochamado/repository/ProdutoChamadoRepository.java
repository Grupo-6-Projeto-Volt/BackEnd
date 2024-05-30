package sptech.school.projetovolt.entity.produtochamado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;

import java.util.List;

public interface ProdutoChamadoRepository extends JpaRepository<ProdutoChamado, Integer> {
    List<ProdutoChamado> findByOrderByDataHoraAberturaAsc();
    List<ProdutoChamado> findByOrderByDataHoraAberturaDesc();
}
