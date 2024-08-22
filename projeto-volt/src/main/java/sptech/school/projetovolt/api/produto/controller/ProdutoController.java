package sptech.school.projetovolt.api.produto.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.service.produto.dto.ProdutoAlteracaoDto;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.produto.dto.ProdutoCriacaoDTO;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.service.produto.ProdutoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Responsável pelo gerenciamento dos produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

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
    public ResponseEntity<ProdutoConsultaDTO> cadastrarProduto(@RequestBody @Valid ProdutoCriacaoDTO produtoNovo) {
        Produto produtoSalvo = produtoService.cadastrarProduto(ProdutoMapper.toEntity(produtoNovo));
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
        List<Produto> produtosEncontrados = produtoService.listarProdutos(textoBusca);

        if (produtosEncontrados.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ProdutoMapper.toDto(produtosEncontrados));

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
        Produto produtoEncontrado = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(ProdutoMapper.toDto(produtoEncontrado));
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
    public ResponseEntity<ProdutoConsultaDTO> alterarProdutoPorId(@PathVariable int id, @RequestBody @Valid ProdutoAlteracaoDto produtoAlterado) {
        Produto produtoAtualizado = produtoService.alterarProdutoPorId(id, ProdutoMapper.fromDtoAlteracaoToEntity(produtoAlterado));
        return ResponseEntity.ok(ProdutoMapper.toDto(produtoAtualizado));
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
         produtoService.deletarProdutoPorId(id);
         return ResponseEntity.noContent().build();
    }

    @GetMapping("/ofertas")
    public ResponseEntity<List<ProdutoConsultaDTO>> buscarOfertas(){
        List<Produto> produtos = produtoService.buscarOfertas();

        if(produtos.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ProdutoMapper.toDto(produtos));
    }

    @GetMapping("/filtro/filtrar-por-preco")
    public ResponseEntity<List<ProdutoConsultaDTO>> filtrarPorPreco(@RequestParam String direcao){
        List<Produto> produtosEncontrados = produtoService.filtrarPorPreco(direcao);

        if (produtosEncontrados.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ProdutoMapper.toDto(produtosEncontrados));
    }

    @GetMapping("/filtro/filtrar-por-desconto")
    public ResponseEntity<List<ProdutoConsultaDTO>> filtrarPorDesconto(@RequestParam String direcao){
        List<Produto> produtosEncontrados = produtoService.filtrarPorDesconto(direcao);

        if (produtosEncontrados.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ProdutoMapper.toDto(produtosEncontrados));
    }


}
