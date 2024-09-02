package sptech.school.projetovolt.api.categoria.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.service.categoria.CategoriaService;
import sptech.school.projetovolt.service.categoria.dto.CategoriaAlteracaoDTO;
import sptech.school.projetovolt.service.categoria.dto.CategoriaConsultaDTO;
import sptech.school.projetovolt.service.categoria.dto.CategoriaCriacaoDTO;
import sptech.school.projetovolt.service.categoria.dto.CategoriaMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaConsultaDTO>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return ResponseUtil.respondIfNotEmpty(CategoriaMapper.toDto(categorias));
    }

    @GetMapping("/buscarPorNomeContendo")
    public ResponseEntity<List<CategoriaConsultaDTO>> buscarCategoriasPorNomeContendo(@RequestParam @Valid String nome) {
        List<Categoria> categorias = categoriaService.buscarCategoriasPorNomeContendo(nome);
        return ResponseUtil.respondIfNotEmpty(CategoriaMapper.toDto(categorias));
    }

    @PostMapping
    public ResponseEntity<CategoriaConsultaDTO> cadastrarCategoria(@RequestBody @Valid CategoriaCriacaoDTO categoriaCriacaoDTO) {
        Categoria entity = CategoriaMapper.toEntity(categoriaCriacaoDTO);
        Categoria categoriaCadastrada = categoriaService.cadastrarCategoria(entity);
        CategoriaConsultaDTO dto = CategoriaMapper.toDto(categoriaCadastrada);
        return ResponseUtil.respondCreated(dto, "/categorias", dto.getId());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaConsultaDTO> alterarCategoria(@PathVariable int id, @RequestParam @Valid String nome) {
        Categoria categoriaAtualizada = categoriaService.alterarCategoria(id, nome);
        return ResponseUtil.respondIfNotNull(CategoriaMapper.toDto(categoriaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable int id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
