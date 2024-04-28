package sptech.school.projetovolt.api.tag.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/tags")
@Tag(name = "Tags", description = "Responsável pelo gerenciamento das tags dos produtos")
public class TagProdutoController {
    @Autowired
    private TagProdutoRepository tagProdutoRepository;

    @PostMapping
    @Operation(
            summary = "Cria uma tag",
            method = "POST",
            description = "Responsável por criar uma tag sem precisar atrelar um produto a ela, inicialmente",
            tags = {"Tags"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Tag criada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content(mediaType = "application/json"),
                    headers = @Header(name = "error", description = "Erro na requisição")
            )

    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto TagProdutoCriacaoDto que representa a tag a ser criada",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagProdutoCriacaoDto.class)
            )
    )
    public ResponseEntity<TagProdutoConsultaDto> criarTag(@RequestBody TagProdutoCriacaoDto tag){

            TagProduto tagSalva = tagProdutoRepository.save(TagProdutoMapper.toEntity(tag));
            return ResponseEntity.status(201).body(TagProdutoMapper.toDto(tagSalva));
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Altera uma tag",
            method = "PATCH",
            description = "Responsável por alterar uma tag",
            tags = {"Tags"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tag alterada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content(mediaType = "application/json"),
                    headers = @Header(name = "error", description = "Erro na requisição")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tag não encontrada",
                    content = @Content(mediaType = "application/json"),
                    headers = @Header(name = "error", description = "Tag não encontrada")
            )
    })
    @Parameter(name = "id", description = "ID do produto", example = "1", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Tag a ser alterada",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)
            )
    )
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
    @Operation(
            summary = "Deleta uma tag",
            method = "DELETE",
            description = "Responsável por deletar uma tag",
            tags = {"Tags"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Tag deletada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content(mediaType = "application/json"),
                    headers = @Header(name = "error", description = "Erro na requisição")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tag não encontrada",
                    content = @Content(mediaType = "application/json"),
                    headers = @Header(name = "error", description = "Tag não encontrada")
            )

    })
    @Parameter(name = "id", description = "ID do produto", example = "1", required = true)
    public ResponseEntity<Void> deletarTag(@PathVariable int id){
        Optional<TagProduto> encontrado = tagProdutoRepository.findById(id);
        if(encontrado.isPresent()){
            tagProdutoRepository.delete(encontrado.get());
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
