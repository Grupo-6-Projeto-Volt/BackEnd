package sptech.school.projetovolt.api.imagemproduto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;
import sptech.school.projetovolt.service.imagemproduto.ImagemProdutoService;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemAtualizacaoDto;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemConsultaDto;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemCriacaoDto;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemProdutoMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/imagem-produtos")
public class ImagemProdutoController {

    private final ImagemProdutoService imagemProdutoService;

    @PostMapping
    @Operation(summary = "Adicionar uma imagem", method = "POST", description = "Responsável por adicionar uma imagem", tags = {"Imagens de Produtos"})
    public ResponseEntity<ImagemConsultaDto> adicionarImagem(@RequestBody @Valid ImagemCriacaoDto novaImagem) {
        ImagemProduto imagemCriada = imagemProdutoService.adicionarImagem(
                ImagemProdutoMapper.toEntity(novaImagem), novaImagem.getIdProduto()
        );
        return ResponseUtil.respondCreated(
                ImagemProdutoMapper.toDto(imagemCriada), "/imagem-produtos", imagemCriada.getId()
        );
    }

    @GetMapping
    @Operation(summary = "Listar todas as imagens", method = "GET", description = "Responsável por listar todas as imagens cadastradas", tags = {"Imagens de Produtos"})
    public ResponseEntity<List<ImagemConsultaDto>> listarImagens() {
        List<ImagemProduto> imagensEncontradas = imagemProdutoService.listarImagens();
        return ResponseUtil.respondIfNotEmpty(ImagemProdutoMapper.toDto(imagensEncontradas));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar imagem por ID", method = "GET", description = "Responsável por buscar uma imagem por ID", tags = {"Imagens de Produtos"})
    public ResponseEntity<ImagemConsultaDto> buscarImagemPorId(@PathVariable Integer id) {
        ImagemProduto imagemEncontrada = imagemProdutoService.buscarImagemPorId(id);
        return ResponseUtil.respondIfNotNull(ImagemProdutoMapper.toDto(imagemEncontrada));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar imagem", method = "PUT", description = "Responsável por atualizar uma imagem", tags = {"Imagens de Produtos"})
    public ResponseEntity<ImagemConsultaDto> atualizarImagem(
            @PathVariable Integer id,
            @RequestBody @Valid ImagemAtualizacaoDto novaImagem)
    {
        ImagemProduto imagemAtualizada = imagemProdutoService.atualizarImagemPorId(id,
                ImagemProdutoMapper.toEntity(novaImagem)
        );
        return ResponseUtil.respondIfNotNull(ImagemProdutoMapper.toDto(imagemAtualizada));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar imagem por ID", method = "DELETE", description = "Responsável por deletar uma imagem por ID", tags = {"Imagens de Produtos"})
    public ResponseEntity<Void> deletarImagemPorId(@PathVariable Integer id) {
        imagemProdutoService.deletarImagemPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar-imagens-produto/{idProduto}")
    public ResponseEntity<Void> deletarImagensDoProdutoPorIdProduto(@PathVariable Integer idProduto) {
        imagemProdutoService.deletarTodasImagensProduto(idProduto);
        return ResponseEntity.noContent().build();
    }
}

