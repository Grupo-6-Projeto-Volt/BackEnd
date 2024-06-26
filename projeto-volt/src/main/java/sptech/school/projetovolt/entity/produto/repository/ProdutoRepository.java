package sptech.school.projetovolt.entity.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    ProdutoConsultaDTO findByNome(String nome);
    List<ProdutoConsultaDTO> findAllByNome(String textoBusca);

    //Optional<Produto> findById(int id);
//    @Query(value = "SELECT p.nome, p.descricao, p.categoria" +
//            ",p.preco, p.qtd_estoque qtdEstoque, p.estado_geral estadoGeral " +
//            "FROM tb_produto AS p WHERE LOWER(p.nome) LIKE LOWER(:textoBusca) LIMIT 1")
//    Produto findByNomeLike(String textoBusca);
}
