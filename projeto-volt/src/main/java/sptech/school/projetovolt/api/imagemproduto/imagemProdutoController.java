package sptech.school.projetovolt.api.imagemproduto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;
import sptech.school.projetovolt.service.imagemproduto.ImagemProdutoService;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemAtualizacaoDto;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemConsultaDto;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemCriacaoDto;
import sptech.school.projetovolt.service.imagemproduto.dto.ImagemProdutoMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/imagemProdutos")
public class imagemProdutoController {

    private final ImagemProdutoService imagemProdutoService;

    @PostMapping
    public ResponseEntity<ImagemConsultaDto> adicionarImagem(@RequestBody @Valid ImagemCriacaoDto novaImagem) {
        ImagemProduto imagemCriada = imagemProdutoService
                .adicionarImagem(ImagemProdutoMapper.toEntity(novaImagem), novaImagem.getIdProduto());
        return ResponseEntity
                .created(URI.create("/imagemProdutos/" + imagemCriada.getId()))
                .body(ImagemProdutoMapper.toDto(imagemCriada));
    }

    @GetMapping
    public ResponseEntity<List<ImagemConsultaDto>> listarImagens() {
        List<ImagemProduto> imagensEncontradas = imagemProdutoService.listarImagens();

        if (imagensEncontradas.isEmpty()) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok(ImagemProdutoMapper.toDto(imagensEncontradas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagemConsultaDto> buscarImagemPorId(@PathVariable Integer id) {
        ImagemProduto imagemEncontrada = imagemProdutoService.buscarImagemPorId(id);
        return ResponseEntity.ok(ImagemProdutoMapper.toDto(imagemEncontrada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagemConsultaDto> atualizarImagem(
            @PathVariable Integer id,
            @RequestBody @Valid ImagemAtualizacaoDto novaImagem)
    {
        ImagemProduto imagemAtualizada = imagemProdutoService
               .atualizarImagemPorId(id, ImagemProdutoMapper.toEntity(novaImagem));

        return ResponseEntity
               .ok(ImagemProdutoMapper.toDto(imagemAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarImagemPorId(@PathVariable Integer id) {
        imagemProdutoService.deletarImagemPorId(id);
        return ResponseEntity.noContent().build();
    }

}
