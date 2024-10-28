package sptech.school.projetovolt.entity.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.school.projetovolt.entity.produto.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findAllByNome(String textoBusca);

    @Query(value = "SELECT * FROM tb_produto p LIMIT :limite", nativeQuery = true)
    List<Produto> findAll(@Param("limite") int limite);

    @Query(value = "SELECT * " +
            "FROM tb_produto p " +
            "WHERE fnRemoveAccents(lower(p.nome)) COLLATE utf8mb4_general_ci LIKE LOWER(CONCAT('%', :textoBusca, '%')) " +
            "LIMIT :limite", nativeQuery = true)
    List<Produto> findAllByNomeContainsIgnoreCase(@Param("textoBusca") String textoBusca, @Param("limite") int limite);

    List<Produto> findByOrderByPrecoDesc();

    List<Produto> findByOrderByPreco();

    @Query("SELECT p " +
            "FROM Produto p " +
            "INNER JOIN p.categoria c " +
            "WHERE c.nome LIKE :categoria")
    List<Produto> buscaProdutoPorCategoria(@Param("categoria") String categoria);

    @Query(value = "SELECT * " +
            "FROM tb_produto p " +
            "WHERE p.desconto is not null " +
            "and p.desconto > 0", nativeQuery = true)
    List<Produto> findByDescontoNotNull();

    List<Produto> findByOrderByDesconto();

    List<Produto> findByOrderByDescontoDesc();

    // Produtos mais clicados
    @Query(value = "SELECT p.id, " +
            "p.nome, " +
            "p.descricao, " +
            "p.preco, " +
            "p.qtd_estoque, " +
            "p.estado_geral, " +
            "p.desconto, " +
            "p.fk_categoria, " +
            "p.data_inicio_desconto, " +
            "p.data_fim_desconto " +
            "FROM tb_produto p " +
            "INNER JOIN tb_click_produto cp ON p.id = cp.fk_produto " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(cp.fk_produto) DESC", nativeQuery = true)
    List<Produto> produtosMaisClicados();

    // Produtos recomendados
    @Query(value = "SELECT p.* " +
            "FROM tb_produto p " +
            "LEFT JOIN tb_favoritos f ON p.id = f.fk_produto AND f.fk_usuario = :usuarioId " +
            "LEFT JOIN tb_click_produto cp ON p.id = cp.fk_produto AND cp.fk_usuario = :usuarioId " +
            "JOIN tb_produto p2 ON p.fk_categoria = p2.fk_categoria " +
            "WHERE p.id != p2.id " +
            "GROUP BY p.id " +
            "ORDER BY " +
            "COUNT(CASE WHEN f.fk_produto IS NOT NULL THEN 1 END) DESC, " +
            "COUNT(CASE WHEN cp.fk_produto IS NOT NULL THEN 1 END) DESC " +
            "LIMIT :limite" , nativeQuery = true)
    List<Produto> buscaProdutosRecomendados(@Param("usuarioId") int usuarioId, @Param("limite") int limite);

}