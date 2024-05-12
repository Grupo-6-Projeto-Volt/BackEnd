package sptech.school.projetovolt.entity.listaFavoritos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.listaFavoritos.ListaFavorita;

public interface ListaFavoritosRepository extends JpaRepository<ListaFavorita, Integer> {

}
