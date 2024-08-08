package sptech.school.projetovolt.entity.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    List<Produto> findAllByNomeContainsIgnoreCase(String textoBusca);

    List<Produto> findByOrderByPrecoDesc();

    List<Produto> findByOrderByPreco();

    //Optional<Produto> findById(int id);
//    @Query(value = "SELECT p.nome, p.descricao, p.categoria" +
//            ",p.preco, p.qtd_estoque qtdEstoque, p.estado_geral estadoGeral " +
//            "FROM tb_produto AS p WHERE LOWER(p.nome) LIKE LOWER(:textoBusca) LIMIT 1")
//    Produto findByNomeLike(String textoBusca);
}
