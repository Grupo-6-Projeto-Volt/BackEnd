package sptech.school.projetovolt.service.classificacaoproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.classificacaoproduto.ClassificacaoProduto;
import sptech.school.projetovolt.entity.classificacaoproduto.repository.ClassificacaoProdutoRepository;
import sptech.school.projetovolt.entity.exception.ConflictException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.tagProduto.TagProdutoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassificacaoProdutoService {

    private final ClassificacaoProdutoRepository classificacaoProdutoRepository;
    private final ProdutoService produtoService;
    private final TagProdutoService tagProdutoService;

    public ClassificacaoProduto associarTagProduto(Integer idProduto, Integer idTag) {

        Produto produtoEncontrado = produtoService.buscarProdutoPorId(idProduto);
        TagProduto tagEncontrada = tagProdutoService.buscarTagPorId(idTag);

        if (classificacaoProdutoRepository.existsByTagProdutoAndProduto(tagEncontrada, produtoEncontrado)) {
            throw new ConflictException("TagProduto [Tag: " + idTag + ", Produto: " + idProduto + "]");
        }

        ClassificacaoProduto novaClassificacao = new ClassificacaoProduto();
        novaClassificacao.setProduto(produtoEncontrado);
        novaClassificacao.setTagProduto(tagEncontrada);

        return classificacaoProdutoRepository.save(novaClassificacao);
    }

    public List<ClassificacaoProduto> listarClassificacoesProdutos() {
        return classificacaoProdutoRepository.findAll();
    }

    public ClassificacaoProduto buscarClassificacaoProdutoPorId(Integer id) {
        return classificacaoProdutoRepository
               .findById(id)
               .orElseThrow(() -> new NotFoundException("ClassificacaoProduto " + id));
    }

    public ClassificacaoProduto atualizarClassificacaoProduto(Integer id, Integer idProduto, Integer idTag) {

        ClassificacaoProduto classificacaoEncontrada = buscarClassificacaoProdutoPorId(id);
        Produto produtoEncontrado = produtoService.buscarProdutoPorId(idProduto);
        TagProduto tagEncontrada = tagProdutoService.buscarTagPorId(idTag);

        if (classificacaoProdutoRepository.existsByTagProdutoAndProduto(tagEncontrada, produtoEncontrado)) {
            throw new ConflictException("TagProduto " + id + " [Tag: " + idTag + ", Produto: " + idProduto + "]");
        }

        classificacaoEncontrada.setProduto(produtoEncontrado);
        classificacaoEncontrada.setTagProduto(tagEncontrada);

        return classificacaoProdutoRepository.save(classificacaoEncontrada);
    }

    public void deletarClassificacaoProdutoPorId(Integer id) {
        if (!classificacaoProdutoRepository.existsById(id)) {
            throw new NotFoundException("ClassificacaoProduto " + id);
        }
        classificacaoProdutoRepository.deleteById(id);
    }

    public void deletarTodasTagsProduto(Integer idProduto) {
        if (!classificacaoProdutoRepository.existsByProdutoId(idProduto)) throw  new NotFoundException("Não existe tag associada ao produto de id " + idProduto);
        classificacaoProdutoRepository.deleteByProdutoId(idProduto);
    }

}
