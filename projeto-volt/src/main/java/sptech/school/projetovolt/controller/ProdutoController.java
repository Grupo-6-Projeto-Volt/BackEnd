package sptech.school.projetovolt.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.model.ProdutoModel;
import sptech.school.projetovolt.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final List<ProdutoModel> produtos = new ArrayList<>();
    private final ProdutoService produtoService = new ProdutoService();
    private Integer id = 0;

    @PostMapping("/estoque")
    @Operation(summary = "Responsável por cadastrar um produto no estoque")
    public ResponseEntity<ProdutoModel> cadastrarProduto(@RequestBody ProdutoModel produtoNovo) {
        if (produtoNovo.getDescProduto() != null
                && produtoNovo.getPrecoProduto() != null
                && produtoNovo.getNomeProduto() != null
                && produtoNovo.getQtdEstoque() != null
        ) {
            if (!produtoService.existePorNome(produtoNovo.getNomeProduto(),produtos)){
                produtoNovo.setIdProduto(++id);
                produtos.add(produtoNovo);
                return ResponseEntity.status(201).body(produtoNovo);
            }
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/loja")
    @Operation(summary = "Responsável por listar todos os produtos da Loja")
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos(@RequestParam(required = false) String textoBusca) {
        List<ProdutoModel> produtosEncontrados = produtos;
        if (textoBusca != null) {
            produtosEncontrados = produtos
                    .stream()
                    .filter(produtoModel -> produtoModel
                            .getNomeProduto()
                            .contains(textoBusca))
                    .toList();
        }
        if (!produtosEncontrados.isEmpty()) {
            return ResponseEntity.status(200).body(produtosEncontrados);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/loja/{id}")
    @Operation(summary = "Responsável por buscar um produto por ID")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable int id) {
        for (ProdutoModel produto : produtos) {
            if (produto.getIdProduto() == id) {
                return ResponseEntity.status(200).body(produto);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/estoque/{id}")
    @Operation(summary = "Responsável por alterar um produto a partir do seu ID")
    public ResponseEntity<ProdutoModel> alterarProdutoPorId(@PathVariable int id, @RequestBody ProdutoModel produtoAlterado) {
        for (ProdutoModel produto : produtos) {
            if (produto.getIdProduto() == id) {
                produtoAlterado.setIdProduto(produto.getIdProduto());
                produtos.set(produtos.indexOf(produto), produtoAlterado);
                return ResponseEntity.status(200).body(produtoAlterado);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/estoque")
    @Operation(summary = "Responsável por alterar o nomde de um determinado produto")
    public ResponseEntity<ProdutoModel> alterarProdutoPorNome(@RequestParam String nomeProduto, @RequestBody ProdutoModel produtoAlterado) {
        for (ProdutoModel produto : produtos) {
            if (produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                produtoAlterado.setIdProduto(produto.getIdProduto());
                produtos.set(produtos.indexOf(produto), produtoAlterado);
                return ResponseEntity.status(200).body(produtoAlterado);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/estoque/{id}")
    @Operation(summary = "Responsável por deletar um determinado produto por ID")
    public ResponseEntity<ProdutoModel> apagarProdutoPorId(@PathVariable int id) {
        for (ProdutoModel produto : produtos) {
            if (produto.getIdProduto() == id) {
                produtos.remove(produto);
                return ResponseEntity.status(204).build();
            }
        }
        return ResponseEntity.status(404).build();
    }

}
