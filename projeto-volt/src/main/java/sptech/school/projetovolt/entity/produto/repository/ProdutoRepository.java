package sptech.school.projetovolt.entity.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.produto.Produto;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
