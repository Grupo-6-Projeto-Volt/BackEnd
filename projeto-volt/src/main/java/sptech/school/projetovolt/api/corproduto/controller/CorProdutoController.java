package sptech.school.projetovolt.api.corproduto.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.corProduto.CorProduto;
import sptech.school.projetovolt.service.corproduto.CorProdutoService;
import sptech.school.projetovolt.service.corproduto.dto.CorProdutoConsultaDTO;
import sptech.school.projetovolt.service.corproduto.dto.CorProdutoCriacaoDTO;
import sptech.school.projetovolt.service.corproduto.dto.CorProdutoMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cor")
public class CorProdutoController {

    private final CorProdutoService corProdutoService;

    @GetMapping
    @Operation(summary = "Listar todas as cores", method = "GET", description = "Responsável por listar todas as cores cadastradas", tags = {"Cores"})
    public ResponseEntity<List<CorProdutoConsultaDTO>> listarCores() {
        List<CorProduto> cores = corProdutoService.listarCores();
        return ResponseUtil.respondIfNotEmpty(CorProdutoMapper.toDto(cores));
    }

    @GetMapping("/buscarPorNome")
    @Operation(summary = "Buscar cor por nome", method = "GET", description = "Responsável por buscar cor por nome", tags = {"Cores"})
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorNome(@RequestParam @Valid String nome) {
        CorProduto cor = corProdutoService.buscarCorPorNome(nome);
        return ResponseUtil.respondIfNotNull(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/buscarPorHex")
    @Operation(summary = "Buscar cor por hex", method = "GET", description = "Responsável por buscar cor por hex", tags = {"Cores"})
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorHex(@RequestParam @Valid String hex) {
        CorProduto cor = corProdutoService.buscarCorPorHex(hex);
        return ResponseUtil.respondIfNotNull(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cor por ID", method = "GET", description = "Responsável por buscar cor por ID", tags = {"Cores"})
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorId(@PathVariable @Valid int id) {
        CorProduto cor = corProdutoService.buscarCorPorId(id);
        return ResponseUtil.respondIfNotNull(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/produtos/{id}")
    @Operation(summary = "Buscar cores por produto", method = "GET", description = "Responsável por buscar cores por produto", tags = {"Cores"})
    public ResponseEntity<List<CorProdutoConsultaDTO>> buscarCoresPorProduto(@PathVariable @Valid int id) {
        List<CorProduto> cores = corProdutoService.buscarCoresPorProduto(id);
        return ResponseUtil.respondIfNotEmpty(CorProdutoMapper.toDto(cores));
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma cor", method = "POST", description = "Responsável por cadastrar uma cor", tags = {"Cores"})
    public ResponseEntity<CorProdutoConsultaDTO> criarCor(@RequestBody @Valid CorProdutoCriacaoDTO corCriacao) {
        CorProduto novaCor = corProdutoService.criarCor(CorProdutoMapper.toEntity(corCriacao));
        return ResponseUtil.respondCreated(CorProdutoMapper.toDto(novaCor), "/cor", novaCor.getId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma cor por ID", method = "DELETE", description = "Responsável por deletar uma cor por ID", tags = {"Cores"})
    public ResponseEntity<Void> deletarCorPorId(@PathVariable int id) {
        corProdutoService.deletarCorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
