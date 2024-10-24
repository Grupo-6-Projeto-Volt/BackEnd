package sptech.school.projetovolt.entity.imagemproduto.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Integer> {
    boolean existsByProdutoId(Integer idProduto);

    @Transactional
    void deleteByProdutoId(Integer idProduto);
}
