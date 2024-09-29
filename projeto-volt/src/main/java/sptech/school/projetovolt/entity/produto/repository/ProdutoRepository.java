package sptech.school.projetovolt.entity.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    /*
    * FIXME: findByNome e FindAllByNome fazem a mesma coisa, a não ser que exista um caso específico em
    *  que estas sejam aplicaveis separadamente.
    * */
    ProdutoConsultaDTO findByNome(String nome);
    List<Produto> findAllByNome(String textoBusca);

    @Query(value = "SELECT * FROM tb_produto p WHERE fnRemoveAccents(lower(p.nome)) COLLATE utf8mb4_0900_ai_ci LIKE LOWER(CONCAT('%', ?, '%'))", nativeQuery = true)
    List<Produto> findAllByNomeContainsIgnoreCase(String textoBusca);

    List<Produto> findByOrderByPrecoDesc();

    List<Produto> findByOrderByPreco();

    @Query("SELECT p FROM Produto p INNER JOIN p.categoria c WHERE c.nome LIKE :categoria")
    List<Produto> buscaProdutoPorCategoria(@Param("categoria") String categoria);

    @Query(value = "SELECT * FROM tb_produto p WHERE p.desconto is not null and p.desconto > 0", nativeQuery = true)
    List<Produto> findByDescontoNotNull();
    //Optional<Produto> findById(int id);
//    @Query(value = "SELECT p.nome, p.descricao, p.categoria" +
//            ",p.preco, p.qtd_estoque qtdEstoque, p.estado_geral estadoGeral " +
//            "FROM tb_produto AS p WHERE LOWER(p.nome) LIKE LOWER(:textoBusca) LIMIT 1")
//    Produto findByNomeLike(String textoBusca);

    List<Produto> findByOrderByDesconto();
    List<Produto> findByOrderByDescontoDesc();
}
