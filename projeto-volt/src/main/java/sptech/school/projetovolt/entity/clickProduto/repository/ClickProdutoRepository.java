package sptech.school.projetovolt.entity.clickProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.clickProduto.ClickProduto;

import java.util.List;

public interface ClickProdutoRepository extends JpaRepository<ClickProduto, Integer> {
    @Query(value = "SELECT * FROM tb_click_produto AS cp ORDER BY cp.data_hora_click",
    nativeQuery = true)
    List<ClickProduto> findAllOrderByDataHoraClick();

    @Query(value = "SELECT * FROM tb_click_produto WHERE fk_produto=:idProduto",
    nativeQuery = true)
    List<ClickProduto> findAllByIdProduto(int idProduto);
}
