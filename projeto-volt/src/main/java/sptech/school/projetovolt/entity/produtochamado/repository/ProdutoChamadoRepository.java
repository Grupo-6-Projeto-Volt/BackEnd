package sptech.school.projetovolt.entity.produtochamado.repository;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;

import java.time.LocalDateTime;
import java.util.List;    

public interface ProdutoChamadoRepository extends JpaRepository<ProdutoChamado, Integer> {
    List<ProdutoChamado> findByOrderByDataHoraAberturaAsc();
    List<ProdutoChamado> findByOrderByDataHoraAberturaDesc();

    @Query(value = "SELECT SUM(tb_produto.preco) FROM tb_produto_chamado JOIN tb_produto" +
            " ON fk_produto = tb_produto.id WHERE status_chamado = 2", nativeQuery = true)
    Double faturamento();
  
    List<ProdutoChamado> findByStatusChamadoOrderByDataHoraAberturaAsc(Integer status);
    List<ProdutoChamado> findByStatusChamadoOrderByDataHoraAberturaDesc(Integer status);

    List<ProdutoChamado> findByStatusChamadoAndDataHoraAberturaAfterOrderByDataHoraAberturaDesc(Integer status, LocalDateTime dataHora);

    @Query("""
               select pc from ProdutoChamado pc
               left join pc.usuario u
               where (pc.statusChamado is null or pc.statusChamado != 2) and u.categoria = 0
               and pc.usuario.id in (
                                  select u.id from ProdutoChamado pc2
                                  left join pc2.usuario u2
                                  where (pc2.statusChamado is null or pc2.statusChamado != 2) and u2.categoria = 0
                                  group by u2.id
                              )
               order by pc.usuario.nome
            """)
    List<ProdutoChamado> buscarLeadsPorNomeCrescente();

    @Query("""
               select pc from ProdutoChamado pc
               left join pc.usuario u
               where (pc.statusChamado is null or pc.statusChamado != 2) and u.categoria = 0
               and pc.usuario.id in (
                                  select u.id from ProdutoChamado pc2
                                  left join pc2.usuario u2
                                  where (pc2.statusChamado is null or pc2.statusChamado != 2) and u2.categoria = 0
                                  group by u2.id
                              )
               order by pc.usuario.nome desc
            """)
    List<ProdutoChamado> buscarLeadsPorNomeDecrescente();
}
