package sptech.school.projetovolt.service.favorito;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.favoritos.Favoritos;
import sptech.school.projetovolt.entity.favoritos.repository.FavoritosRepository;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritosRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    public Favoritos criar(Favoritos favorito, Integer idUsuario, Integer idProduto){
        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
        Optional<Produto> optProduto = produtoRepository.findById(idProduto);
        if(optProduto.isEmpty() || optUsuario.isEmpty()){
            throw new NotFoundException("Produto ou usuário");
        }
        favorito.setProduto(optProduto.get());
        favorito.setUsuario(optUsuario.get());
        return favoritoRepository.save(favorito);
    }

    public List<Favoritos> listarPorUsuario(Integer idUsuario){
        return favoritoRepository.findAllByFkUsuario(idUsuario);
    }

    public void excluir(Integer id){
        Optional<Favoritos> favoritoParaExcluir = favoritoRepository.findById(id);
        if(favoritoParaExcluir.isEmpty()){
            throw new NotFoundException("Favorito");
        }
        favoritoRepository.delete(favoritoParaExcluir.get());
    }

    public boolean isProdutoFavoritadoPorUsuario(int idUsuario, int idProduto) {
        return favoritoRepository.findByUsuarioIdAndProdutoId(idUsuario, idProduto) != null;
    }

}
