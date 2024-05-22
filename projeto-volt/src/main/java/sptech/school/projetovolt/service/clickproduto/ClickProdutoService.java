package sptech.school.projetovolt.service.clickproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.entity.clickProduto.repository.ClickProdutoRepository;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.usuario.repository.UsuarioRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClickProdutoService {
    private final ClickProdutoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public ClickProduto criar(ClickProduto novoClick, Integer idUsuario, Integer idProduto){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

        if(usuarioOpt.isEmpty()){
            throw new NotFoundException("Usuário");
        }
        if(produtoOpt.isEmpty()){
            throw new NotFoundException("Produto");
        }
        novoClick.setProduto(produtoOpt.get());
        novoClick.setUsuario(usuarioOpt.get());

        return repository.save(novoClick);
    }


}
