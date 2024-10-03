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
import sptech.school.projetovolt.entity.vwmaisclicados.VwMaisClicados;
import sptech.school.projetovolt.entity.vwmaisclicados.repository.VwMaisClicadosRepository;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoConsultaDTO;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoMaisClicadosDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClickProdutoService {
    private final ClickProdutoRepository repository;
    private final VwMaisClicadosRepository vwRepository;
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

    public List<VwMaisClicados> listarMaisClicados(){
        return vwRepository.produtosMaisClicados();
    }

    public List<Produto> listarMaisClicadosProdutos(Integer qtd){
        if(qtd != null){
            return produtoRepository.produtosMaisClicados().subList(0, qtd);
        }
        return produtoRepository.produtosMaisClicados();
    }

    public List<ClickProduto> listarOrdenadoPorData(){
        return repository.findAllOrderByDataHoraClick();
    }

    public List<ClickProduto> listarPorProduto(Integer idProduto){
        return repository.findAllByIdProduto(idProduto);
    }
}
