package sptech.school.projetovolt.entity.corProduto.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.corProduto.CorProduto;

import java.util.Optional;

public interface CorProdutoRepository extends JpaRepository<CorProduto, Integer> {

    Optional<CorProduto> findByNome(String nome);

    Optional<CorProduto> findByHexId(String hexId);

    boolean existsByProdutoId(Integer idProduto);

    @Transactional
    void deleteByProdutoId(Integer idProduto);

}
