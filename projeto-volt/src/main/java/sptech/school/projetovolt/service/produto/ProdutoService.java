package sptech.school.projetovolt.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.entity.corProduto.CorProduto;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.service.categoria.CategoriaService;
import sptech.school.projetovolt.service.corproduto.CorProdutoService;
import sptech.school.projetovolt.service.imagemproduto.ImagemProdutoService;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.utils.HashTableObj;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ImagemProdutoService imagemProdutoService;
    private final CorProdutoService corProdutoService;
    private final CategoriaService categoriaService;
    private final HashTableObj<String> hashTable;

    public Produto cadastrarProduto (Produto produto, Integer idCategoria, List<ProdutoConsultaDTO.ImagemProduto> imagens, List<ProdutoConsultaDTO.TagProduto> tags, List<ProdutoConsultaDTO.CorProduto> cores) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);
        produto.setCategoria(categoria);
        Produto produtoCriado = produtoRepository.save(produto);

        for (int i = 0; i < imagens.size(); i++) {
            ImagemProduto novaImagem = new ImagemProduto();
            novaImagem.setNome(imagens.get(i).getNome());
            novaImagem.setCodigoImagem(imagens.get(i).getCodigoImagem());
            novaImagem.setIndiceVt(imagens.get(i).getIndiceVt());
            novaImagem.setProduto(produtoCriado);
            imagemProdutoService.adicionarImagem(novaImagem);
        }

//        for (int i = 0; i < cores.size(); i++) {
//            CorProduto novaCor = new CorProduto();
//            novaCor.setNome(cores.get(i).getNome());
//            novaCor.setHexId(cores.get(i).getHexId());
//            novaCor.setProduto(produtoCriado);
//            corProdutoService.criarCor(novaCor);
//        }

        hashTable.put(produto.getNome().toLowerCase());
        return produtoCriado;
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
