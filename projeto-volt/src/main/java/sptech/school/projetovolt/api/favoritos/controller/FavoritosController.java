package sptech.school.projetovolt.api.favoritos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.favoritos.Favoritos;
import sptech.school.projetovolt.service.favorito.FavoritoService;
import sptech.school.projetovolt.service.favorito.dto.FavoritoConsultaDTO;
import sptech.school.projetovolt.service.favorito.dto.FavoritoCriacaoDTO;
import sptech.school.projetovolt.service.favorito.dto.FavoritoMapper;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.usuario.UsuarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritoService service;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<FavoritoConsultaDTO> criar(@RequestParam int idUsuario, @RequestParam int idProduto, @RequestBody(required = false) @Valid FavoritoCriacaoDTO novoFavorito) {
        Favoritos entity = service.isProdutoFavoritadoPorUsuario(idUsuario, idProduto);
        if (entity != null) {
            service.excluir(entity.getId());
            return ResponseEntity.noContent().build(); // Favorito removido
        }
        if (novoFavorito == null) {
            return ResponseEntity.badRequest().build(); // Body de FavoritoCriacaoDTO é obrigatório para criar um novo favorito
        }
        Favoritos criado = FavoritoMapper.toEntity(novoFavorito,
                produtoService.buscarProdutoPorId(idProduto),
                usuarioService.buscarUsuarioPorId(idUsuario));
        Favoritos salvo = service.criar(criado, idUsuario, idProduto);
        return ResponseUtil.respondCreated(FavoritoMapper.toDto(salvo), "/favoritos", salvo.getId());
    }

    @GetMapping("/lista-por-usuario")
    public ResponseEntity<List<FavoritoConsultaDTO>> listarPorUsuario(@RequestParam int idUsuario) {
        return ResponseUtil.respondIfNotEmpty(FavoritoMapper.toDto(service.listarPorUsuario(idUsuario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/is-favoritado")
    public ResponseEntity<FavoritoConsultaDTO> isProdutoFavoritadoPorUsuario(@RequestParam int idUsuario, @RequestParam int idProduto) {
        FavoritoConsultaDTO dto = FavoritoMapper.toDto(service.isProdutoFavoritadoPorUsuario(idUsuario, idProduto));
        return ResponseEntity.ok(dto);
    }
}
