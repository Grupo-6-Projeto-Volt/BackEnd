package sptech.school.projetovolt.api.favoritos.controller;

import io.swagger.v3.oas.annotations.Operation;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritoService service;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Cadastrar um favorito", method = "POST", description = "Responsável por cadastrar um favorito", tags = {"Favoritos"})
    public ResponseEntity<FavoritoConsultaDTO> criar(@RequestParam(required = false) Integer idUsuario, @RequestParam(required = false) Integer idProduto, @RequestBody(required = false) @Valid FavoritoCriacaoDTO novoFavorito) {
        if (novoFavorito != null) {
            Favoritos criado = FavoritoMapper.toEntity(novoFavorito,
                    produtoService.buscarProdutoPorId(novoFavorito.getIdProduto()),
                    usuarioService.buscarUsuarioPorId(novoFavorito.getIdUsuario()));
            Favoritos salvo = service.criar(criado, novoFavorito.getIdUsuario(), novoFavorito.getIdProduto());
            return ResponseUtil.respondCreated(FavoritoMapper.toDto(salvo), "/favoritos", salvo.getId());
        }

        if (idUsuario == null || idProduto == null) return ResponseEntity.badRequest().build();

        Favoritos entity = service.isProdutoFavoritadoPorUsuario(idUsuario, idProduto);
        service.excluir(entity.getId());
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista-por-usuario")
    @Operation(summary = "Listar favoritos por usuário", method = "GET", description = "Responsável por listar os favoritos por usuário", tags = {"Favoritos"})
    public ResponseEntity<List<FavoritoConsultaDTO>> listarPorUsuario(@RequestParam int idUsuario) {
        return ResponseUtil.respondIfNotEmpty(FavoritoMapper.toDto(service.listarPorUsuario(idUsuario)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um favorito", method = "DELETE", description = "Responsável por excluir um favorito", tags = {"Favoritos"})
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/is-favoritado")
    @Operation(summary = "Verificar se um produto é favoritado por um usuário", method = "GET", description = "Responsável por verificar se um produto é favoritado por um usuário", tags = {"Favoritos"})
    public ResponseEntity<FavoritoConsultaDTO> isProdutoFavoritadoPorUsuario(@RequestParam int idUsuario, @RequestParam int idProduto) {
        FavoritoConsultaDTO dto = FavoritoMapper.toDto(service.isProdutoFavoritadoPorUsuario(idUsuario, idProduto));
        return ResponseEntity.ok(dto);
    }
}
