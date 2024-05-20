package sptech.school.projetovolt.api.produtochamado.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;
import sptech.school.projetovolt.service.produtochamado.ProdutoChamadoService;
import sptech.school.projetovolt.service.produtochamado.dto.ProdutoChamadoConsultaDto;
import sptech.school.projetovolt.service.produtochamado.dto.ProdutoChamadoCriacaoDto;
import sptech.school.projetovolt.service.produtochamado.dto.ProdutoChamadoMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtochamados")
@Tag(name = "ProdutoChamado", description = "Responsável pelo gerenciamento de chamados de produtos")
public class ProdutoChamadoController {

    private final ProdutoChamadoService produtoChamadoService;

    @PostMapping
    public ResponseEntity<ProdutoChamadoConsultaDto> criarProdutoChamado(@RequestParam Integer idUsuario, @RequestParam Integer idProduto) {

        ProdutoChamado produtoChamadoSalvo = produtoChamadoService.salvarProdutoChamado(idUsuario, idProduto);

        URI uri = URI.create("/produtochamados" + produtoChamadoSalvo.getId());

        ProdutoChamadoConsultaDto dto = ProdutoChamadoMapper.toDto(produtoChamadoSalvo);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoChamadoConsultaDto>> listarProdutoChamados() {
        List<ProdutoChamado> produtoChamados = produtoChamadoService.listarProdutoChamados();

        if (produtoChamados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProdutoChamadoConsultaDto> dtos = ProdutoChamadoMapper.toDto(produtoChamados);

        return ResponseEntity.ok(dtos);
    }

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<ProdutoChamadoConsultaDto> cancelarProdutoChamado(@PathVariable Integer id) {
        ProdutoChamado produtoChamadoCancelado = produtoChamadoService.cancelarProdutoChamado(id);

        ProdutoChamadoConsultaDto dto = ProdutoChamadoMapper.toDto(produtoChamadoCancelado);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<ProdutoChamadoConsultaDto> concluirProdutoChamado(@PathVariable Integer id) {
        ProdutoChamado produtoChamadoConcluido = produtoChamadoService.concluirProdutoChamado(id);

        ProdutoChamadoConsultaDto dto = ProdutoChamadoMapper.toDto(produtoChamadoConcluido);

        return ResponseEntity.ok(dto);
    }
}


