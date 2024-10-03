package sptech.school.projetovolt.api.classificacaoproduto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.classificacaoproduto.ClassificacaoProduto;
import sptech.school.projetovolt.service.classificacaoproduto.ClassificacaoProdutoService;
import sptech.school.projetovolt.service.classificacaoproduto.dto.ClassificacaoProdutoConsultaDto;
import static sptech.school.projetovolt.service.classificacaoproduto.dto.ClassificacaoProdutoMapper.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classificacao-produtos")
public class ClassificacaoProdutoController {

    private final ClassificacaoProdutoService classificacaoProdutoService;

    @PostMapping
    public ResponseEntity<ClassificacaoProdutoConsultaDto> associarTagProuduto(
            @RequestParam Integer idProduto,
            @RequestParam Integer idTag
    ) {
        ClassificacaoProduto classificacaoCriada = classificacaoProdutoService.associarTagProduto(idProduto, idTag);
        return ResponseEntity
               .created(URI.create("/classificacao-produtos/" + classificacaoCriada.getId()))
               .body(toDto(classificacaoCriada));
    }

    @GetMapping
    public ResponseEntity<List<ClassificacaoProdutoConsultaDto>> listarClassificacaoProduto() {
        List<ClassificacaoProduto> classificacoesEncontradas = classificacaoProdutoService.listarClassificacoesProdutos();
        if (classificacoesEncontradas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity
               .ok(toDto(classificacoesEncontradas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassificacaoProdutoConsultaDto> buscarClassificacaoProdutoPorId(@PathVariable Integer id) {
        ClassificacaoProduto classificacaoEncontrada = classificacaoProdutoService.buscarClassificacaoProdutoPorId(id);
        return ResponseEntity.ok(toDto(classificacaoEncontrada));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClassificacaoProdutoConsultaDto> atualizarClassificacaoProduto(
            @PathVariable Integer id,
            @RequestParam Integer idProduto,
            @RequestParam Integer idTag
    ) {
        ClassificacaoProduto classificacaoAlterada = classificacaoProdutoService
                .atualizarClassificacaoProduto(id, idProduto, idTag);
        return ResponseEntity.ok(toDto(classificacaoAlterada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClassificacaoProdutoPorId(@PathVariable Integer id) {
        classificacaoProdutoService.deletarClassificacaoProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar-tags-produto/{idProduto}")
    public ResponseEntity<Void> deletarTagsDoProdutoPorIdProduto(@PathVariable Integer idProduto) {
        classificacaoProdutoService.deletarTodasTagsProduto(idProduto);
        return ResponseEntity.noContent().build();
    }

}
