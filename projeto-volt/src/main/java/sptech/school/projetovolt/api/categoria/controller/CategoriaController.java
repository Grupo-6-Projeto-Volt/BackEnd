package sptech.school.projetovolt.api.categoria.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.service.categoria.CategoriaService;
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
    @Operation(summary = "Listar todas as categorias", method = "GET", description = "Responsável por listar todas as categorias cadastradas", tags = {"Categorias"})
    public ResponseEntity<List<CategoriaConsultaDTO>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return ResponseUtil.respondIfNotEmpty(CategoriaMapper.toDto(categorias));
    }

    @GetMapping("/buscarPorNomeContendo")
    @Operation(summary = "Buscar categorias por nome contendo", method = "GET", description = "Responsável por buscar categorias por nome contendo", tags = {"Categorias"})
    public ResponseEntity<List<CategoriaConsultaDTO>> buscarCategoriasPorNomeContendo(@RequestParam @Valid String nome) {
        List<Categoria> categorias = categoriaService.buscarCategoriasPorNomeContendo(nome);
        return ResponseUtil.respondIfNotEmpty(CategoriaMapper.toDto(categorias));
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma categoria", method = "POST", description = "Responsável por cadastrar uma categoria", tags = {"Categorias"})
    public ResponseEntity<CategoriaConsultaDTO> cadastrarCategoria(@RequestBody @Valid CategoriaCriacaoDTO categoriaCriacaoDTO) {
        Categoria entity = CategoriaMapper.toEntity(categoriaCriacaoDTO);
        Categoria categoriaCadastrada = categoriaService.cadastrarCategoria(entity);
        CategoriaConsultaDTO dto = CategoriaMapper.toDto(categoriaCadastrada);
        return ResponseUtil.respondCreated(dto, "/categorias", dto.getId());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Alterar uma categoria", method = "PATCH", description = "Responsável por alterar uma categoria", tags = {"Categorias"})
    public ResponseEntity<CategoriaConsultaDTO> alterarCategoria(@PathVariable int id, @RequestParam @Valid String nome) {
        Categoria categoriaAtualizada = categoriaService.alterarCategoria(id, nome);
        return ResponseUtil.respondIfNotNull(CategoriaMapper.toDto(categoriaAtualizada));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma categoria", method = "DELETE", description = "Responsável por deletar uma categoria", tags = {"Categorias"})
    public ResponseEntity<Void> deletarCategoria(@PathVariable int id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/exportar", produces = "text/csv")
    @Operation(summary = "Exportar categorias para arquivo CSV", method = "POST", description = "Responsável por exportar categorias para arquivo CSV", tags = {"Categorias"})
    public ResponseEntity<byte[]> exportarArquivo(@RequestBody List<Categoria> categorias, HttpServletResponse response){
        if(categorias.isEmpty()) return null;
        try {
            return ResponseEntity.ok(categoriaService.gravarArquivo(categorias,response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
