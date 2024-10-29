package sptech.school.projetovolt.service.categoria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.entity.categoria.repository.CategoriaRepository;
import sptech.school.projetovolt.entity.exception.ConflictException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.service.categoria.dto.CategoriaConsultaDTO;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoConsultaDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria cadastrarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria alterarCategoria(Integer id, String nome) {
        Categoria categoriaEncontrada = buscarCategoriaPorId(id);

        if (categoriaRepository.existsByNome(nome)) {
            throw new ConflictException("Categoria");
        }

        categoriaEncontrada.setNome(nome);
        return categoriaRepository.save(categoriaEncontrada);
    }

    public void deletarCategoria(Integer id) {
        buscarCategoriaPorId(id);
        categoriaRepository.deleteById(id);
    }

    public Categoria buscarCategoriaPorId(Integer id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        return categoriaOpt.orElseThrow(() -> new NotFoundException("Categoria"));
    }

    public List<Categoria> buscarCategoriasPorNomeContendo(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Categoria buscarCategoriasPorNome(String nome) {
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findByNome(nome);
        return categoriaEncontrada.orElseThrow(() -> new NotFoundException("Categoria " + nome));
    }
    public byte[] gravarArquivo(List<CategoriaConsultaDTO> categorias, HttpServletResponse response) {
        String arquivo = "categorias.csv";
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo + "\"");

        try{
            return gerarArquivo(categorias);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public byte[] exportarJson(List<CategoriaConsultaDTO> categorias) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsBytes(categorias);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao serializar os dados para JSON", e);
        }
    }

    public byte[] exportarXml(List<CategoriaConsultaDTO> categorias) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<categorias>\n");

        for (CategoriaConsultaDTO categoria : categorias) {
            xml.append(String.format("    <categoria id=\"%d\">%s</categoria>\n", categoria.getId(), categoria.getNome()));
        }

        xml.append("</categorias>");

        return xml.toString().getBytes(StandardCharsets.UTF_8);

    }

    public byte[] exportarParquet(List<CategoriaConsultaDTO> categorias) {
        StringBuilder sb = new StringBuilder();

        // cabeçalho básico
        sb.append("PARQUET_SIMULADO\n");
        sb.append("Versão: 1.0\n");
        sb.append("Colunas: id, nome\n");
        sb.append("Registro de dados:\n");

        for (CategoriaConsultaDTO categoria : categorias) {
            sb.append("ID:").append(categoria.getId()).append(";");
            sb.append("Nome:").append(categoria.getNome()).append(";");
            sb.append("\n");
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private byte[] gerarArquivo(List<CategoriaConsultaDTO> categorias){
        try(ByteArrayOutputStream saidaByte = new ByteArrayOutputStream()){
            OutputStreamWriter writer = new OutputStreamWriter(saidaByte,StandardCharsets.UTF_8);
            writer.write("Id;Categoria\n");
            for (CategoriaConsultaDTO categoria : categorias) {
                writer.write(String.format("%d;%s\n",categoria.getId(),categoria.getNome()));
            }
            writer.flush();
            return saidaByte.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
