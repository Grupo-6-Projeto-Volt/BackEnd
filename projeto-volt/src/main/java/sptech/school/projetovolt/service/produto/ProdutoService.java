package sptech.school.projetovolt.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto cadastrarProduto (Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos (String textoBusca) {
        if (textoBusca != null) {
            return produtoRepository.findAllByNome(textoBusca)
                    .stream()
                    .filter(produtoModel -> produtoModel
                            .getNome()
                            .contains(textoBusca))
                    .toList();
        }

        return produtoRepository.findAll();
    }

    public List<Produto> buscarOfertas(){
        return produtoRepository.findByDescontoNotNull();
    }

    public Produto buscarProdutoPorId (int id) {
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto " + id));
    }

    public Produto alterarProdutoPorId (Integer id, Produto produto) {
        Produto produtoEncontrado = buscarProdutoPorId(id);
        return produtoRepository.save(produto);
    }

    public void deletarProdutoPorId (Integer id) {
        Produto produtoEncotrado = buscarProdutoPorId(id);
        produtoRepository.deleteById(id);
    }

    public List<Produto> filtrarPorPreco (String direcao) {
        if (direcao == null || direcao.equalsIgnoreCase("asc")) {
            return produtoRepository.findByOrderByPreco();
        }
        return produtoRepository.findByOrderByPrecoDesc();
    }

}
