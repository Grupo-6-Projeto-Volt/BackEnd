package sptech.school.projetovolt.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.service.categoria.CategoriaService;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.utils.HashTableObj;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;
    private final HashTableObj<String> hashTable;

    public Produto cadastrarProduto (Produto produto, Integer idCategoria) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);

        produto.setCategoria(categoria);
        hashTable.put(produto.getNome().toLowerCase());
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos(String textoBusca) {
        if (textoBusca != null && !textoBusca.isEmpty()) {
            String textoNormalizado = Normalizer.normalize(textoBusca, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}", "")
                    .toLowerCase();
            return produtoRepository.findAllByNomeContainsIgnoreCase(textoNormalizado);
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

    public Produto alterarProdutoPorId (Integer id, Produto produto, Integer idCategoria) {
        buscarProdutoPorId(id);
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);
        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }

    public void deletarProdutoPorId (Integer id) {
        buscarProdutoPorId(id);
        produtoRepository.deleteById(id);
    }

    public List<Produto> filtrarPorPreco (String direcao) {
        if (direcao == null || direcao.equalsIgnoreCase("asc")) {
            return produtoRepository.findByOrderByPreco();
        }
        return produtoRepository.findByOrderByPrecoDesc();
    }

    public List<Produto> filtrarPorDesconto (String direcao) {
        if (direcao == null || direcao.equalsIgnoreCase("asc")) {
            return produtoRepository.findByOrderByDesconto();
        }
        return produtoRepository.findByOrderByDescontoDesc();
    }

    public List<Produto> buscarProdutosPorCategoria(String categoria) {
        return produtoRepository.buscaProdutoPorCategoria(categoria);
    }

}
