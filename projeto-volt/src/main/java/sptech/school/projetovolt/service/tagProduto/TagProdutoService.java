package sptech.school.projetovolt.service.tagProduto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.ConflictException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.entity.tagProduto.repository.TagProdutoRepository;

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
public class TagProdutoService {

    private final TagProdutoRepository tagProdutoRepository;

    public TagProduto criarTag (TagProduto novaTag) {
        if (tagProdutoRepository.existsByTag(novaTag.getTag())) {
            throw new ConflictException("TagProduto " + novaTag.getTag());
        }
        return tagProdutoRepository.save(novaTag);
    }

    public List<TagProduto> listarTags () {
        return tagProdutoRepository.findAll();
    }

    public TagProduto buscarTagPorId (Integer id) {
        Optional<TagProduto> tagEncontrada = tagProdutoRepository.findById(id);
        return tagEncontrada.orElseThrow(() -> new NotFoundException("TagProduto " + id));
    }

    public TagProduto atualizarTag (Integer id, String tag) {
        TagProduto tagEncontrada = buscarTagPorId(id);

        if (tagProdutoRepository.existsByTag(tag)) {throw new ConflictException("TagProduto " + id);}

        tagEncontrada.setTag(tag);
        return tagProdutoRepository.save(tagEncontrada);
    }

    public void deletarTagPorId (Integer id) {
        buscarTagPorId(id);
        tagProdutoRepository.deleteById(id);
    }

    public byte[] gravarArquivo(List<TagProduto> tags,HttpServletResponse response) {
        String arquivo = "tags.csv";
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + arquivo + "\"");

        try{
            return gerarArquivo(tags);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    private byte[] gerarArquivo(List<TagProduto> tagProdutos){
        try(ByteArrayOutputStream saidaByte = new ByteArrayOutputStream()){
            OutputStreamWriter writer = new OutputStreamWriter(saidaByte,StandardCharsets.UTF_8);
            writer.write("Id;Tag\n");
            for (TagProduto tagProduto : tagProdutos) {
                writer.write(String.format("%d;%s\n",tagProduto.getId(),tagProduto.getTag()));
            }
            writer.flush();
            Files.write(Paths.get("./tags.csv"),saidaByte.toByteArray());
            return saidaByte.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }




}
