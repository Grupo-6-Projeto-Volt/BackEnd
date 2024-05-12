package sptech.school.projetovolt.entity.favoritos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.favoritos.Favorito;

public interface FavoritosRepository extends JpaRepository<Favorito, Integer> {
}
