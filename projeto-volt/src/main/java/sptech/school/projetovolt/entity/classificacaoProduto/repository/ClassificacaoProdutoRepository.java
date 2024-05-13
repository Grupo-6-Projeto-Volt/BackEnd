package sptech.school.projetovolt.entity.classificacaoProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.classificacaoProduto.ClassificacaoProduto;

public interface ClassificacaoProdutoRepository extends JpaRepository<ClassificacaoProduto, Integer> {
}
