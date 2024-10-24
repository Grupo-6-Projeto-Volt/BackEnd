package sptech.school.projetovolt.api.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.service.tagProduto.TagProdutoService;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoConsultaDto;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoCriacaoDto;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoMapper;
import sptech.school.projetovolt.utils.ListaObj;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagProdutoController {

    private final TagProdutoService tagProdutoService;

    @PostMapping
    @Operation(summary = "Cadastrar uma tag", method = "POST", description = "Responsável por cadastrar uma tag", tags = {"Tags"})
    public ResponseEntity<TagProdutoConsultaDto> criarTag(@RequestBody @Valid TagProdutoCriacaoDto novaTag) {
        TagProduto tagCriada = tagProdutoService.criarTag(TagProdutoMapper.toEntity(novaTag));
        return ResponseUtil.respondCreated(TagProdutoMapper.toDto(tagCriada), "/tags", tagCriada.getId());
    }

    @GetMapping
    @Operation(summary = "Listar todas as tags", method = "GET", description = "Responsável por listar todas as tags cadastradas", tags = {"Tags"})
    public ResponseEntity<ListaObj<TagProdutoConsultaDto>> listarTags() {
        List<TagProduto> tagsEncontradas = tagProdutoService.listarTags();

        if (tagsEncontradas.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(TagProdutoMapper.toDto(tagsEncontradas));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tag por ID", method = "GET", description = "Responsável por buscar uma tag por ID", tags = {"Tags"})
    public ResponseEntity<TagProdutoConsultaDto> buscaTag(@PathVariable int id) {
        TagProduto tagEncontrada = tagProdutoService.buscarTagPorId(id);
        return ResponseUtil.respondIfNotNull(TagProdutoMapper.toDto(tagEncontrada));
    }

    @GetMapping("/buscar-tag-por-nome")
    public ResponseEntity<TagProdutoConsultaDto> buscaTagPorNome(@RequestParam String tag) {
        TagProduto tagEncontrada = tagProdutoService.buscarTagPorNome(tag);
        return ResponseUtil.respondIfNotNull(TagProdutoMapper.toDto(tagEncontrada));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Alterar tag", method = "PATCH", description = "Responsável por alterar uma tag", tags = {"Tags"})
    public ResponseEntity<TagProdutoConsultaDto> alterarTag(@PathVariable int id, @RequestParam @Valid String tag) {
        TagProduto tagAlterada = tagProdutoService.atualizarTag(id, tag);
        return ResponseUtil.respondIfNotNull(TagProdutoMapper.toDto(tagAlterada));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tag", method = "DELETE", description = "Responsável por deletar uma tag", tags = {"Tags"})
    public ResponseEntity<Void> deletarTag(@PathVariable int id) {
        tagProdutoService.deletarTagPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/exportar", produces = "text/csv")
    @Operation(summary = "Exportar arquivo", method = "POST", description = "Responsável por exportar um arquivo", tags = {"Tags"})
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
