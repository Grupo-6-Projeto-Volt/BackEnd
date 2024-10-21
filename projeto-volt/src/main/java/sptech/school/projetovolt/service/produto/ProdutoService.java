package sptech.school.projetovolt.service.produto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.service.categoria.CategoriaService;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.utils.HashTableObj;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;

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

    public List<Produto> listarProdutos(String textoBusca, Integer limite) {
        if (textoBusca != null && !textoBusca.isEmpty()) {
            String textoNormalizado = Normalizer.normalize(textoBusca, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}", "")
                    .toLowerCase();
            return produtoRepository.findAllByNomeContainsIgnoreCase(textoNormalizado, limite);
        }
        return produtoRepository.findAll(limite);
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

    public byte[] gravarArquivo(List<ProdutoConsultaDTO> produtos, HttpServletResponse response) {
        String arquivo = "produtos.csv";
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + arquivo + "\"");

        try{
            return gerarArquivo(produtos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private byte[] gerarArquivo(List<ProdutoConsultaDTO> produtos){
        try(ByteArrayOutputStream saidaByte = new ByteArrayOutputStream()){
            OutputStreamWriter writer = new OutputStreamWriter(saidaByte, StandardCharsets.UTF_8);
            writer.write("Id;Nome;Estado;Preço;Categoria\n");
            for (ProdutoConsultaDTO produto : produtos) {
                writer.write(String.format("%d;%s;%s;%f;%s\n",produto.getId(),produto.getNome(),produto.getEstadoGeral(),produto.getPreco(),produto.getCategoria()));
            }
            writer.flush();
            Files.write(Paths.get("./produtos.csv"),saidaByte.toByteArray());
            return saidaByte.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Produto> buscarProdutosRecomendados(Integer idUser, Integer limite) {
        // FIXME: Implementar lógica de recomendação quando o idUser for null
        return produtoRepository.buscaProdutosRecomendados(Objects.requireNonNullElse(idUser, 1), limite);
    }
}
