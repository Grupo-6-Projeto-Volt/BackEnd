package sptech.school.projetovolt.entity.tagProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

import java.util.Optional;

public interface TagProdutoRepository extends JpaRepository<TagProduto, Integer> {

    boolean existsByTag(String tag);
    Optional<TagProduto> findByTag(String tag);

}
