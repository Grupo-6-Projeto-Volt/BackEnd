package sptech.school.projetovolt.service.favorito;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.favoritos.Favorito;
import sptech.school.projetovolt.entity.favoritos.repository.FavoritosRepository;
import sptech.school.projetovolt.entity.listaFavoritos.ListaFavorita;
import sptech.school.projetovolt.entity.listaFavoritos.repository.ListaFavoritosRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListaFavoritoService {
    private final ListaFavoritosRepository listaFavoritosRepository;
    private final FavoritosRepository favoritoRepository;

    public ListaFavorita criar(ListaFavorita listaFavorita, Integer favoritoId){
        Optional<Favorito> favorito = favoritoRepository.findById(favoritoId);
        if(favorito.isEmpty()){

        }
        listaFavorita.setFavoritos(favorito.get());
        return listaFavoritosRepository.save(listaFavorita);
    }
}
