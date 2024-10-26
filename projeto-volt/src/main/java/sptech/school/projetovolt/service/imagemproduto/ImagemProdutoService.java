package sptech.school.projetovolt.service.imagemproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;
import sptech.school.projetovolt.entity.imagemproduto.repository.ImagemProdutoRepository;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.service.produto.ProdutoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagemProdutoService {

    private final ImagemProdutoRepository imagemProdutoRepository;
    private final ProdutoService produtoService;

    public ImagemProduto adicionarImagem(ImagemProduto novaImagem, Integer idProduto) {
        Produto produto = produtoService.buscarProdutoPorId(idProduto);
        novaImagem.setProduto(produto);
        return imagemProdutoRepository.save(novaImagem);
    }

    public List<ImagemProduto> listarImagens() {
        return imagemProdutoRepository.findAll();
    }

    public ImagemProduto buscarImagemPorId(Integer id) {
        return imagemProdutoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imagem com ID " + id + " não encontrada."));
    }

    public ImagemProduto atualizarImagemPorId(Integer id, ImagemProduto novaImagem) {
        ImagemProduto imagemEncontrada = buscarImagemPorId(id);
        novaImagem.setId(imagemEncontrada.getId());
        novaImagem.setProduto(imagemEncontrada.getProduto());
        return imagemProdutoRepository.save(novaImagem);
    }

    public void deletarImagemPorId(Integer id) {
        if (!imagemProdutoRepository.existsById(id)) throw new NotFoundException("Imagem com ID " + id + " não encontrada.");
        imagemProdutoRepository.deleteById(id);
    }

    public void deletarTodasImagensProduto(Integer idProduto) {
        if (!imagemProdutoRepository.existsByProdutoId(idProduto)) throw  new NotFoundException("Não existe imagem associada ao produto de id " + idProduto);
        imagemProdutoRepository.deleteByProdutoId(idProduto);
    }


}
