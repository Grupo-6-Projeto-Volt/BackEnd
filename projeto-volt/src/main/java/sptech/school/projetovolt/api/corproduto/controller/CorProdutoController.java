package sptech.school.projetovolt.api.corproduto.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.corProduto.CorProduto;
import sptech.school.projetovolt.service.corproduto.CorProdutoService;
import sptech.school.projetovolt.service.corproduto.dto.CorProdutoConsultaDTO;
import sptech.school.projetovolt.service.corproduto.dto.CorProdutoCriacaoDTO;
import sptech.school.projetovolt.service.corproduto.dto.CorProdutoMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cor")
public class CorProdutoController {

    private final CorProdutoService corProdutoService;

    @GetMapping()
    public ResponseEntity<List<CorProdutoConsultaDTO>> listarCores() {
        List<CorProduto> cores = corProdutoService.listarCores();

        if (cores.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(CorProdutoMapper.toDto(cores));
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorNome(@RequestParam @Valid String nome) {
        CorProduto cor = corProdutoService.buscarCorPorNome(nome);

        if (cor == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/buscarPorHex")
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorHex(@RequestParam @Valid String hex) {
        CorProduto cor = corProdutoService.buscarCorPorHex(hex);

        if (cor == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(CorProdutoMapper.toDto(cor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorProdutoConsultaDTO> buscarCorPorId(@PathVariable @Valid int id) {
        CorProduto cor = corProdutoService.buscarCorPorId(id);

        if (cor == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(CorProdutoMapper.toDto(cor));
    }

    @PostMapping()
    public ResponseEntity<CorProdutoConsultaDTO> criarCor(@RequestBody @Valid CorProdutoCriacaoDTO corCriacao) {
        CorProduto novaCor = corProdutoService.criarCor(CorProdutoMapper.toEntity(corCriacao));
        CorProdutoConsultaDTO dto = CorProdutoMapper.toDto(novaCor);

        URI uri = URI.create("/cor/" + dto.getId());

        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCorPorId(@PathVariable int id) {
        corProdutoService.deletarCorPorId(id);
        return ResponseEntity.noContent().build();
    }




}
