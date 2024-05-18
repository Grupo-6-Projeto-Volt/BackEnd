package sptech.school.projetovolt.api.favoritos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.favoritos.Favoritos;
import sptech.school.projetovolt.service.favorito.FavoritoService;
import sptech.school.projetovolt.service.favorito.dto.FavoritoConsultaDTO;
import sptech.school.projetovolt.service.favorito.dto.FavoritoCriacaoDTO;
import sptech.school.projetovolt.service.favorito.dto.FavoritoMapper;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.usuario.UsuarioService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritoService service;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<FavoritoConsultaDTO> criar(@RequestBody @Valid FavoritoCriacaoDTO novoFavorito){
        URI favorito = URI.create("/favoritos");
        Favoritos criado = FavoritoMapper.toEntity(novoFavorito,
                produtoService.buscarProdutoPorId(novoFavorito.getIdProduto()),
                usuarioService.buscarUsuarioPorId(novoFavorito.getIdUsuario()));

        return ResponseEntity.created(favorito).body(FavoritoMapper.toDto(
                service.criar(criado,
                        criado.getUsuario().getId(),
                        criado.getProduto().getId())));
    }

    @GetMapping("/lista-por-usuario")
    public ResponseEntity<List<FavoritoConsultaDTO>> listarPorUsuario(@RequestParam int idUsuario){
        return ResponseEntity.ok(FavoritoMapper.toDto(service.listarPorUsuario(idUsuario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
