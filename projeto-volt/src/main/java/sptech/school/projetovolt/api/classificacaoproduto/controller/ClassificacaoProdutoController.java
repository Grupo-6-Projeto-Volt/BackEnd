package sptech.school.projetovolt.api.classificacaoproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Cadastrar uma classificação de produto", method = "POST", description = "Responsável por cadastrar uma classificação de produto", tags = {"Classificação de Produto"})
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
    @Operation(summary = "Listar todas as classificações de produtos", method = "GET", description = "Responsável por listar todas as classificações de produtos cadastradas", tags = {"Classificação de Produto"})
    public ResponseEntity<List<ClassificacaoProdutoConsultaDto>> listarClassificacaoProduto() {
        List<ClassificacaoProduto> classificacoesEncontradas = classificacaoProdutoService.listarClassificacoesProdutos();
        if (classificacoesEncontradas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity
               .ok(toDto(classificacoesEncontradas));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar classificação de produto por ID", method = "GET", description = "Responsável por buscar uma classificação de produto por ID", tags = {"Classificação de Produto"})
    public ResponseEntity<ClassificacaoProdutoConsultaDto> buscarClassificacaoProdutoPorId(@PathVariable Integer id) {
        ClassificacaoProduto classificacaoEncontrada = classificacaoProdutoService.buscarClassificacaoProdutoPorId(id);
        return ResponseEntity.ok(toDto(classificacaoEncontrada));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar classificação de produto", method = "PATCH", description = "Responsável por atualizar uma classificação de produto", tags = {"Classificação de Produto"})
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
    @Operation(summary = "Deletar classificação de produto por ID", method = "DELETE", description = "Responsável por deletar uma classificação de produto por ID", tags = {"Classificação de Produto"})
    public ResponseEntity<Void> deletarClassificacaoProdutoPorId(@PathVariable Integer id) {
        classificacaoProdutoService.deletarClassificacaoProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }


}
