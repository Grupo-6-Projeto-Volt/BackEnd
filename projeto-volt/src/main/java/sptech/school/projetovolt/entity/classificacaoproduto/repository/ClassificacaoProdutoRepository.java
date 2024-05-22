package sptech.school.projetovolt.entity.classificacaoproduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.classificacaoproduto.ClassificacaoProduto;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

public interface ClassificacaoProdutoRepository extends JpaRepository<ClassificacaoProduto, Integer> {

    boolean existsByTagProdutoAndProduto(TagProduto tagProduto, Produto produto);

}
