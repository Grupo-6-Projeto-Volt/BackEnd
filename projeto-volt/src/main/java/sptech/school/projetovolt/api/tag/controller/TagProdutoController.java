package sptech.school.projetovolt.api.tag.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.entity.tagProduto.repository.TagProdutoRepository;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoConsultaDto;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoCriacaoDto;
import sptech.school.projetovolt.service.tagProduto.dto.TagProdutoMapper;
import sptech.school.projetovolt.utils.ListaObj;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagProdutoController {

    private final TagProdutoRepository tagProdutoRepository;

    @PostMapping
    public ResponseEntity<TagProdutoConsultaDto> criarTag(@RequestBody TagProdutoCriacaoDto tag){
            TagProduto tagSalva = tagProdutoRepository.save(TagProdutoMapper.toEntity(tag));

            return ResponseEntity.status(201).body(TagProdutoMapper.toDto(tagSalva));
    }

    @GetMapping
    public ResponseEntity<ListaObj<TagProdutoConsultaDto>> listarTags(){
        List<TagProduto> tags = tagProdutoRepository.findAll();

        if(tags.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(TagProdutoMapper.toDto(tags));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagProdutoConsultaDto> buscaTag(@PathVariable int id){
        Optional<TagProduto> tagOpt = tagProdutoRepository.findById(id);

        if(tagOpt.isPresent()) return ResponseEntity.ok(TagProdutoMapper.toDto(tagOpt.get()));

        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TagProdutoConsultaDto> alterarTag(@PathVariable int id, @RequestParam @Valid String tag){
        Optional<TagProduto> tagParaAlterarOpt = tagProdutoRepository.findById(id);

        if(tagParaAlterarOpt.isPresent()){
            TagProduto alterado = tagParaAlterarOpt.get();
            alterado.setTag(tag);
            tagProdutoRepository.save(alterado);

            return ResponseEntity.ok(TagProdutoMapper.toDto(alterado));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTag(@PathVariable int id){
        Optional<TagProduto> encontradoOpt = tagProdutoRepository.findById(id);

        if(encontradoOpt.isPresent()){
            tagProdutoRepository.delete(encontradoOpt.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
