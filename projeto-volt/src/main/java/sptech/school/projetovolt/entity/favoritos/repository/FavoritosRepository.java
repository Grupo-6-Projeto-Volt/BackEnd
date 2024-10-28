package sptech.school.projetovolt.entity.favoritos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetovolt.entity.favoritos.Favoritos;

import java.util.List;

public interface FavoritosRepository extends JpaRepository<Favoritos, Integer> {
    @Query(value = "SELECT * FROM tb_favoritos WHERE fk_usuario = :idUsuario", nativeQuery = true)
    List<Favoritos> findAllByFkUsuario(Integer idUsuario);

    @Query(value = "SELECT * FROM tb_favoritos WHERE fk_usuario = :idUsuario AND fk_produto = :idProduto", nativeQuery = true)
    Favoritos findByUsuarioIdAndProdutoId(int idUsuario, int idProduto);
}
