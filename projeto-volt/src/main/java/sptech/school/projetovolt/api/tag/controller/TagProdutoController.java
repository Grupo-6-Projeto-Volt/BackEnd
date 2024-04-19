package sptech.school.projetovolt.api.tag.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.tag.TagProduto;
import sptech.school.projetovolt.entity.tag.dto.TagProdutoConsultaDto;
import sptech.school.projetovolt.entity.tag.dto.TagProdutoCriacaoDto;
import sptech.school.projetovolt.entity.tag.dto.TagProdutoMapper;
import sptech.school.projetovolt.entity.tag.repository.TagProdutoRepository;

import java.util.Optional;

@RestController
@RequestMapping("/new/tag")
public class TagProdutoController {
    @Autowired
    private TagProdutoRepository tagProdutoRepository;

    @PostMapping
    public ResponseEntity<TagProdutoConsultaDto> criarTag(@RequestBody TagProdutoCriacaoDto tag){
        Optional<TagProduto> tagOpt = tagProdutoRepository.findById(TagProdutoMapper.toEntity(tag).getId());
        if(!tagOpt.get().getTag().equalsIgnoreCase(tag.getTag())){
            TagProduto tagSalva = tagProdutoRepository.save(TagProdutoMapper.toEntity(tag));
            return ResponseEntity.status(201).body(TagProdutoMapper.toDto(tagSalva));
        }
        return ResponseEntity.status(409).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TagProdutoConsultaDto> alterarTag(@PathVariable int id, @RequestParam @Valid String tag){
        Optional<TagProduto> tagParaAlterar = tagProdutoRepository.findById(id);
        if(tagParaAlterar.isPresent()){
            TagProduto alterado = tagParaAlterar.get();
            alterado.setTag(tag);
            tagProdutoRepository.save(alterado);

            return ResponseEntity.status(200).body(TagProdutoMapper.toDto(alterado));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTag(@PathVariable int id){
        Optional<TagProduto> encontrado = tagProdutoRepository.findById(id);
        if(encontrado.isPresent()){
            tagProdutoRepository.delete(encontrado.get());
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}