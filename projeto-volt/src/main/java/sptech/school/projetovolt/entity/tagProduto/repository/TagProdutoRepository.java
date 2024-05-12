package sptech.school.projetovolt.entity.tagProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

public interface TagProdutoRepository extends JpaRepository<TagProduto, Integer> {

    boolean existsByTag(String tag);

}
