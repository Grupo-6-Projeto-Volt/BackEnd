package sptech.school.projetovolt.api.tag.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.service.tagProduto.TagProdutoService;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoConsultaDto;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoCriacaoDto;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoMapper;
import sptech.school.projetovolt.utils.ListaObj;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagProdutoController {

    private final TagProdutoService tagProdutoService;

    @PostMapping
    public ResponseEntity<TagProdutoConsultaDto> criarTag(@RequestBody @Valid TagProdutoCriacaoDto novaTag) {
        TagProduto tagCriada = tagProdutoService.criarTag(TagProdutoMapper.toEntity(novaTag));
        return ResponseUtil.respondCreated(TagProdutoMapper.toDto(tagCriada), "/tags", tagCriada.getId());
    }

    @GetMapping
    public ResponseEntity<ListaObj<TagProdutoConsultaDto>> listarTags() {
        List<TagProduto> tagsEncontradas = tagProdutoService.listarTags();

        if (tagsEncontradas.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(TagProdutoMapper.toDto(tagsEncontradas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagProdutoConsultaDto> buscaTag(@PathVariable int id) {
        TagProduto tagEncontrada = tagProdutoService.buscarTagPorId(id);
        return ResponseUtil.respondIfNotNull(TagProdutoMapper.toDto(tagEncontrada));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TagProdutoConsultaDto> alterarTag(@PathVariable int id, @RequestParam @Valid String tag) {
        TagProduto tagAlterada = tagProdutoService.atualizarTag(id, tag);
        return ResponseUtil.respondIfNotNull(TagProdutoMapper.toDto(tagAlterada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTag(@PathVariable int id) {
        tagProdutoService.deletarTagPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/exportar", produces = "text/csv")
    public ResponseEntity<byte[]> exportarArquivo(@RequestBody List<TagProduto> tags, HttpServletResponse response){
        if(tags.isEmpty()) return null;
        try {
            return ResponseEntity.ok(tagProdutoService.gravarArquivo(tags,response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
