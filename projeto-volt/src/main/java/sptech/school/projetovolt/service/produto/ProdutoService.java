package sptech.school.projetovolt.service.produto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.service.categoria.CategoriaService;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.produto.dto.ProdutoExportacaoDto;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.utils.HashTableObj;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;
    private final HashTableObj<String> hashTable;

    public Produto cadastrarProduto(Produto produto, Integer idCategoria) {
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

    public Produto buscarProdutoPorId(int id) {
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto " + id));
    }

    public Produto alterarProdutoPorId(Integer id, Produto produto, Integer idCategoria) {
        buscarProdutoPorId(id);
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);
        produto.setCategoria(categoria);
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public void deletarProdutoPorId(Integer id) {
        buscarProdutoPorId(id);
        produtoRepository.deleteById(id);
    }

    public List<Produto> filtrarPorPreco(String direcao) {
        if (direcao == null || direcao.equalsIgnoreCase("asc")) {
            return produtoRepository.findByOrderByPreco();
        }
        return produtoRepository.findByOrderByPrecoDesc();
    }

    public List<Produto> filtrarPorDesconto(String direcao) {
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

        try {
            return gerarArquivo(produtos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    private byte[] gerarArquivo(List<ProdutoConsultaDTO> produtos) {
        try (ByteArrayOutputStream saidaByte = new ByteArrayOutputStream()) {
            OutputStreamWriter writer = new OutputStreamWriter(saidaByte, StandardCharsets.UTF_8);
            writer.write("Id;Nome;Estado;Preço;Categoria\n");
            for (ProdutoConsultaDTO produto : produtos) {
                writer.write(String.format("%d;%s;%s;%f;%s\n", produto.getId(), produto.getNome(), produto.getEstadoGeral(), produto.getPreco(), produto.getCategoria()));
            }
            writer.flush();
            return saidaByte.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public byte[] gravarArquivo(String arquivo){
        List<ProdutoConsultaDTO> produtos = ProdutoMapper.toDto(listarProdutos(null,1000));
        int contador = 0;
        ByteArrayOutputStream saidaByte = new ByteArrayOutputStream();
        try(OutputStreamWriter writer = new OutputStreamWriter(saidaByte,StandardCharsets.UTF_8)){
            String header = "00PROD";
            header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            header += "01";
            writer.write(header);
            gravarRegistro(arquivo,header);
        }catch (IOException exception){
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }

        try(OutputStreamWriter writer = new OutputStreamWriter(saidaByte,StandardCharsets.UTF_8)){
            String body;
            for (ProdutoConsultaDTO produto : produtos) {
                body = "02";
                body += String.format("%03d",produto.getId());
                body += String.format("%-120.120s",produto.getNome());
                body += String.format("%-25.25s",produto.getCategoria());
                body += String.format("%08.2f",produto.getPreco());
                body += String.format("%-45.45s",produto.getEstadoGeral());

                writer.write(body);
                gravarRegistro(arquivo,body);
                contador++;
            }
        }catch (IOException exception){
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        try(OutputStreamWriter writer = new OutputStreamWriter(saidaByte,StandardCharsets.UTF_8)){
            String trailer = "01";
            trailer += String.format("%05d",contador);
            writer.write(trailer);
            gravarRegistro(arquivo,trailer);
        }catch (IOException exception){
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }

        return saidaByte.toByteArray();
    }
    private void gravarRegistro(String arquivo,String registro){
        try(BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo,true))){
            saida.append(registro + "\n");
        }catch (IOException exception){
            System.err.printf("Erro ao manipular arquivo %s: %s",arquivo,exception.getMessage());
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void processarArquivoImportacao(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String linha;

            linha = reader.readLine();

            if (linha == null || !linha.startsWith("00")) throw new IllegalArgumentException("Header inválido no arquivo.");


            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("02")) {
                    Produto produto = parseRegistroProduto(linha);
                    produtoRepository.save(produto);
                } else if (linha.startsWith("01")) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo", e);
        }
    }

    private Produto parseRegistroProduto(String linha) {
        Produto produto = new Produto();
        produto.setNome(linha.substring(7, 11).trim());
        produto.setDescricao(linha.substring(11, 411).trim());
        produto.setPreco(Double.parseDouble(linha.substring(411, 418).trim()));
        produto.setQtdEstoque(Integer.parseInt(linha.substring(418, 422).trim()));
        produto.setEstadoGeral(linha.substring(422, 425).trim());
        produto.setDesconto(Integer.parseInt(linha.substring(425, 428).trim()));
        produto.setDataInicioDesconto(parseData(linha.substring(428, 436).trim()));
        produto.setDataFimDesconto(parseData(linha.substring(436, 444).trim()));
        return produto;
    }

    private LocalDate parseData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return LocalDate.parse(data, formatter);
    }

  
    public byte[] exportarJson(List<ProdutoExportacaoDto> produtos) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsBytes(produtos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao serializar os dados para JSON", e);
        }
    }

    public byte[] exportarXml(List<ProdutoExportacaoDto> produtos) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<produtos>\n");

        for (ProdutoExportacaoDto produto : produtos) {
            xml.append("  <produto>\n");
            xml.append("    <id>").append(produto.getId()).append("</id>\n");
            xml.append("    <nome>").append(produto.getNome()).append("</nome>\n");
            xml.append("    <categoria>").append(produto.getCategoria()).append("</categoria>\n");
            xml.append("    <preco>").append(produto.getPreco()).append("</preco>\n");
            xml.append("    <estado>").append(produto.getEstadoGeral()).append("</estado>\n");
            xml.append("  </produto>\n");
        }

        xml.append("</produtos>");

        return xml.toString().getBytes(StandardCharsets.UTF_8);
    }


    public void importarArquivo(String arquivo){

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();
            // Leitura do header do TXT
            if (!linha.startsWith("00")) {
                throw new IllegalArgumentException("Arquivo inválido");
            }
            // Leitura
            while ((linha = reader.readLine()) != null && linha.startsWith("02")) {

                Produto produto = new Produto();
                produto.setId(Integer.parseInt(linha.substring(2, 6).trim()));
                produto.setNome(linha.substring(6, 38).trim());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Produto> buscarProdutosRecomendados(Integer idUser, Integer limite) {
        if(idUser == null) return produtoRepository.buscarProdutosMaioresPromocoes(limite);
        return produtoRepository.buscaProdutosRecomendados(idUser, limite);
    }
}
