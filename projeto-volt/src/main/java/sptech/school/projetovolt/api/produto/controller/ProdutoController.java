package sptech.school.projetovolt.api.produto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.api.util.ResponseUtil;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.service.hashtable.HashTableService;
import sptech.school.projetovolt.service.produto.dto.ProdutoAlteracaoDto;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.produto.dto.ProdutoCriacaoDTO;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.service.produto.ProdutoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Responsável pelo gerenciamento dos produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final HashTableService hashTableService;

    @PostMapping("/estoque")
    @Operation(summary = "Cadastra um produto no estoque", method = "POST", description = "Responsável por cadastrar um produto no estoque", tags = {"Produtos"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<ProdutoConsultaDTO> cadastrarProduto(@RequestBody @Valid ProdutoCriacaoDTO produtoNovo) {
        Produto produtoSalvo = produtoService.cadastrarProduto(ProdutoMapper.toEntity(produtoNovo), produtoNovo.getIdCategoria());
        return ResponseUtil.respondCreated(ProdutoMapper.toDto(produtoSalvo), "/produtos/estoque", produtoSalvo.getId());
    }

    @GetMapping("/loja")
    @Operation(summary = "Lista todos os produtos da Loja", method = "GET", description = "Responsável por listar todos os produtos cadastrados na loja", tags = {"Produtos"})
    public ResponseEntity<List<ProdutoConsultaDTO>> listarTodosProdutos(@RequestParam(required = false) String textoBusca) {
        List<Produto> produtosEncontrados = produtoService.listarProdutos(textoBusca);
        return ResponseUtil.respondIfNotEmpty(ProdutoMapper.toDto(produtosEncontrados));
    }
    @GetMapping("/loja/hash")
    @Operation(summary = "Lista todos os produtos da loja por meio da hash table",method = "GET",description = "Responsável por listar todos os nomes dos produtos cadastrados na loja",tags = {"Produtos"})
    public ResponseEntity<String> buscarProdutoHashTable(@RequestParam String textoBusca){
        String produtoEncontrado = hashTableService.buscarProdutoPorNome(textoBusca);
        return ResponseEntity.ok(produtoEncontrado);
    }

    @PostMapping("loja/hash")
    @Operation(summary = "Inserção de todos os produtos da loja na hash table",method = "POST",description = "Responsável por inserir os produtos na tabela hash",tags = {"Produtos"})
    public ResponseEntity<Void> inserirProdutosHashTable(){
        hashTableService.inserirProdutos();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("loja/hash")
    @Operation(summary = "Deleta um produto da hash table pelo nome",method = "DELETE",description = "Responsável por deletar o produto da hash table a  partir de seu nome",tags = {"Produtos"})
    public ResponseEntity<Void> deletarProdutoHashTable(@RequestParam String textoBusca){
        hashTableService.removerProdutoPorNome(textoBusca);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loja/{id}")
    @Operation(summary = "Busca um produto pelo ID", method = "GET", description = "Responsável por buscar um produto a partir do seu ID", tags = {"Produtos"})
    public ResponseEntity<ProdutoConsultaDTO> buscarProdutoPorId(@PathVariable int id) {
        Produto produtoEncontrado = produtoService.buscarProdutoPorId(id);
        return ResponseUtil.respondIfNotNull(ProdutoMapper.toDto(produtoEncontrado));
    }

    @PutMapping("/estoque/{id}")
    @Operation(summary = "Altera um produto pelo seu ID", method = "PUT", description = "Responsável por Alterar um produto a partir do seu ID", tags = {"Produtos"})
    public ResponseEntity<ProdutoConsultaDTO> alterarProdutoPorId(@PathVariable int id, @RequestBody @Valid ProdutoAlteracaoDto produtoAlterado) {
        Produto produtoAtualizado = produtoService.alterarProdutoPorId(id, ProdutoMapper.fromDtoAlteracaoToEntity(produtoAlterado), produtoAlterado.getIdCategoria());
        return ResponseUtil.respondIfNotNull(ProdutoMapper.toDto(produtoAtualizado));
    }

    @DeleteMapping("/estoque/{id}")
    @Operation(summary = "Deleta um produto pelo ID", method = "DELETE", description = "Responsável por deletar um produto a partir do seu ID", tags = {"Produtos"})
    public ResponseEntity<Void> apagarProdutoPorId(@PathVariable int id) {
        produtoService.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ofertas")
    public ResponseEntity<List<ProdutoConsultaDTO>> buscarOfertas() {
        List<Produto> produtos = produtoService.buscarOfertas();
        return ResponseUtil.respondIfNotEmpty(ProdutoMapper.toDto(produtos));
    }

    @GetMapping("/filtro/filtrar-por-preco")
    public ResponseEntity<List<ProdutoConsultaDTO>> filtrarPorPreco(@RequestParam String direcao) {
        List<Produto> produtosEncontrados = produtoService.filtrarPorPreco(direcao);
        return ResponseUtil.respondIfNotEmpty(ProdutoMapper.toDto(produtosEncontrados));
    }

    @GetMapping("/filtro/filtrar-por-desconto")
    public ResponseEntity<List<ProdutoConsultaDTO>> filtrarPorDesconto(@RequestParam String direcao) {
        List<Produto> produtosEncontrados = produtoService.filtrarPorDesconto(direcao);
        return ResponseUtil.respondIfNotEmpty(ProdutoMapper.toDto(produtosEncontrados));
    }

    @GetMapping("/filtro/filtrar-por-categoria")
    public ResponseEntity<List<ProdutoConsultaDTO>> buscarProdutosPorCategoria(@RequestParam String categoria) {
        List<Produto> produtosEncontrados = produtoService.buscarProdutosPorCategoria(categoria);
        return ResponseUtil.respondIfNotEmpty(ProdutoMapper.toDto(produtosEncontrados));
    }
   @PostMapping(value = "/exportar", produces = "text/csv")
    public ResponseEntity<byte[]> exportarArquivo(@RequestBody List<ProdutoConsultaDTO> produtos, HttpServletResponse response){
        if(produtos.isEmpty()) return null;
        try {
            return ResponseEntity.ok(produtoService.gravarArquivo(produtos,response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping(value = "/exportar-txt",produces = "text/txt")
    public ResponseEntity<byte[]> exportarArquivoTxt(){
        try{
            return ResponseEntity.ok(produtoService.gravarArquivo("produtos.txt"));
        }catch (Exception  e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


}
