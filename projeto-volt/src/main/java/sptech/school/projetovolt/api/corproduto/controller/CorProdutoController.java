package sptech.school.projetovolt.api.corproduto.controller;

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
    public ResponseEntity<List<CorProdutoConsultaDTO>> listarCores() {
        List<CorProduto> cores = corProdutoService.listarCores();
        return ResponseUtil.respondIfNotEmpty(CorProdutoMapper.toDto(cores));
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorNome(@RequestParam @Valid String nome) {
        CorProduto cor = corProdutoService.buscarCorPorNome(nome);
        return ResponseUtil.respondIfNotNull(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/buscarPorHex")
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorHex(@RequestParam @Valid String hex) {
        CorProduto cor = corProdutoService.buscarCorPorHex(hex);
        return ResponseUtil.respondIfNotNull(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorId(@PathVariable @Valid int id) {
        CorProduto cor = corProdutoService.buscarCorPorId(id);
        return ResponseUtil.respondIfNotNull(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<List<CorProdutoConsultaDTO>> buscarCoresPorProduto(@PathVariable @Valid int id) {
        List<CorProduto> cores = corProdutoService.buscarCoresPorProduto(id);
        return ResponseUtil.respondIfNotEmpty(CorProdutoMapper.toDto(cores));
    }

    @PostMapping
    public ResponseEntity<CorProdutoConsultaDTO> criarCor(@RequestBody @Valid CorProdutoCriacaoDTO corCriacao) {
        CorProduto novaCor = corProdutoService.criarCor(CorProdutoMapper.toEntity(corCriacao));
        return ResponseUtil.respondCreated(CorProdutoMapper.toDto(novaCor), "/cor", novaCor.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCorPorId(@PathVariable int id) {
        corProdutoService.deletarCorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
