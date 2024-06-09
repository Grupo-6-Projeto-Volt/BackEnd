package sptech.school.projetovolt.entity.produtochamado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;

import java.util.List;

public interface ProdutoChamadoRepository extends JpaRepository<ProdutoChamado, Integer> {
    List<ProdutoChamado> findByOrderByDataHoraAberturaAsc();
    List<ProdutoChamado> findByOrderByDataHoraAberturaDesc();

    @Query(value = "SELECT SUM(tb_produto.preco) FROM tb_produto_chamado JOIN tb_produto" +
            " ON fk_produto = tb_produto.id WHERE status_chamado = 2", nativeQuery = true)
    Double faturamento();
}
