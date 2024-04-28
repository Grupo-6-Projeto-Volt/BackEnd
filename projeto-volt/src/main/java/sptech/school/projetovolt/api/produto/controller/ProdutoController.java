package sptech.school.projetovolt.api.produto.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.dto.ProdutoAlteracaoDto;
import sptech.school.projetovolt.entity.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.entity.produto.dto.ProdutoCriacaoDTO;
import sptech.school.projetovolt.entity.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.service.produto.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Responsável pelo gerenciamento dos produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    private final ProdutoService produtoService = new ProdutoService();

    @PostMapping("/estoque")
    @Operation(
            summary = "Cadastra um produto no estoque",
            method = "POST",
            description = "Responsável por cadastrar um produto no estoque",
            tags = {"Produtos"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Produto cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Erro na requisição")
            ),

    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Produto Criacao DTO",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProdutoCriacaoDTO.class))
    )
    public ResponseEntity<ProdutoConsultaDTO> cadastrarProduto(@RequestBody ProdutoCriacaoDTO produtoNovo) {
                Produto produtoSalvo = produtoRepository.save(ProdutoMapper.toEntity(produtoNovo));
                return ResponseEntity.status(201).body(ProdutoMapper.toDto(produtoSalvo));
    }

    @GetMapping("/loja")
    @Operation(
            summary = "Lista todos os produtos da Loja",
            method = "GET",
            description = "Responsável por listar todos os produtos cadastrados na loja",
            tags = {"Produtos"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Produtos retornado com sucesso",
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Não contém produtos cadastrados",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Não contém produtos cadastrados")
            )
    })
    @Parameter(name = "textoBusca", description = "Texto para busca de produtos", example = "Iphone", required = false)
    public ResponseEntity<List<ProdutoConsultaDTO>> listarTodosProdutos(@RequestParam(required = false) String textoBusca) {
        List<Produto> produtosEncontrados = produtoRepository.findAll();
        List<ProdutoConsultaDTO> dtos = new ArrayList<>();
        if (textoBusca != null) {
            dtos = produtoRepository.findAllByNome(textoBusca)
                    .stream()
                    .filter(produtoModel -> produtoModel
                            .getNome()
                            .contains(textoBusca))
                    .toList();
        }else {
            for (Produto produto : produtosEncontrados) {
                dtos.add(ProdutoMapper.toDto(produto));
            }
        }
        if (!produtosEncontrados.isEmpty()) {
            return ResponseEntity.status(200).body(dtos);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/loja/{id}")
    @Operation(
            summary = "Busca um produto pelo ID",
            method = "GET",
            description = "Responsável por buscar um produto a partir do seu ID",
            tags = {"Produtos"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Produto encontrado com sucesso",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Produto não encontrado")
            )
    })
    @Parameter(name = "id", description = "ID do produto", example = "1", required = true)
    public ResponseEntity<ProdutoConsultaDTO> buscarProdutoPorId(@PathVariable int id) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);
        if(produtoEncontrado.isPresent()){
            return ResponseEntity.status(200).body(ProdutoMapper.toDto(produtoEncontrado.get()));
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/estoque/{id}")
    @Operation(
            summary = "Altera um produto pelo seu ID",
            method = "PUT",
            description = "Responsável por Alterar um produto a partir do seu ID",
            tags = {"Produtos"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Produto alterado com sucesso",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProdutoConsultaDTO.class)),
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Erro na requisição")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Produto não encontrado")
            )

    })
    @Parameter(name = "id", description = "ID do produto", example = "1", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Produto Alteracao DTO",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProdutoAlteracaoDto.class))
    )
    public ResponseEntity<ProdutoConsultaDTO> alterarProdutoPorId(@PathVariable int id, @RequestBody ProdutoAlteracaoDto produtoAlterado) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if(produtoOpt.isPresent()){
            Produto novoProduto = ProdutoMapper.fromDtoAlteracaoToEntity(produtoAlterado);
            novoProduto.setId(produtoOpt.get().getId());

            produtoRepository.save(novoProduto);

            return ResponseEntity.status(200).body(ProdutoMapper.toDto(novoProduto));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/estoque/{id}")
    @Operation(
            summary = "Deleta um produto pelo ID",
            method = "DELETE",
            description = "Responsável por deletar um produto a partir do seu ID",
            tags = {"Produtos"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Produto deletado com sucesso",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Produto não encontrado")
            )
    })
    @Parameter(name = "id", description = "ID do produto", example = "1", required = true)
    public ResponseEntity<Void> apagarProdutoPorId(@PathVariable int id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if(produtoOpt.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }
}
