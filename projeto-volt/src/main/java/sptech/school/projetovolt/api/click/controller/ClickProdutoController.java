package sptech.school.projetovolt.api.click.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.vwmaisclicados.VwMaisClicados;
import sptech.school.projetovolt.service.clickproduto.ClickProdutoService;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoConsultaDTO;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoCriacaoDTO;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoMaisClicadosDTO;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoMapper;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.usuario.UsuarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clicks-produtos")
@RequiredArgsConstructor
@Slf4j
public class ClickProdutoController {
    private final ClickProdutoService service;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    @GetMapping("/mais-clicados")
    public ResponseEntity<List<ClickProdutoMaisClicadosDTO>> listarMaisClicados(){
        log.debug(service.listarMaisClicados().toString());
        return ResponseEntity.ok(ClickProdutoMapper.vwToDto(service.listarMaisClicados()));
    }

    @PostMapping
    public ResponseEntity<ClickProdutoConsultaDTO> criar(@RequestBody @Valid ClickProdutoCriacaoDTO novoClick){
        return ResponseEntity.created(URI.create("/clicks-produto")).body(
                ClickProdutoMapper.toDto(
                        service.criar(
                                ClickProdutoMapper.toEntity(novoClick,
                                produtoService.buscarProdutoPorId(novoClick.getIdProduto()),
                                usuarioService.buscarUsuarioPorId(novoClick.getIdUsuario())),
                novoClick.getIdUsuario(),
                novoClick.getIdProduto())));
    }

    @GetMapping
    public ResponseEntity<List<ClickProdutoConsultaDTO>> listarOrdenadoPorData(){
        return ResponseEntity.ok(ClickProdutoMapper.toDto(service.listarOrdenadoPorData()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ClickProdutoConsultaDTO>> listarClicksPorProduto(@PathVariable int id){
        return ResponseEntity.ok(ClickProdutoMapper.toDto(service.listarPorProduto(id)));
    }
}