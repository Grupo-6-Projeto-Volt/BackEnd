package sptech.school.projetovolt.api.produto.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    private final List<Produto> produtos = new ArrayList<>();
    private final ProdutoService produtoService = new ProdutoService();
    private Integer id = 0;

    @PostMapping("/estoque")
    @Operation(summary = "Responsável por cadastrar um produto no estoque")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produtoNovo) {
        if (produtoNovo.getDescricao() != null
                && produtoNovo.getPreco() != null
                && produtoNovo.getNome() != null
                && produtoNovo.getQtdEstoque() != null
        ) {
            if (!produtoService.existePorNome(produtoNovo.getNome(),produtos)){
                produtoNovo.setId(++id);
                produtos.add(produtoNovo);
                return ResponseEntity.status(201).body(produtoNovo);
            }
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/loja")
    @Operation(summary = "Responsável por listar todos os produtos da Loja")
    public ResponseEntity<List<Produto>> listarTodosProdutos(@RequestParam(required = false) String textoBusca) {
        List<Produto> produtosEncontrados = produtos;
        if (textoBusca != null) {
            produtosEncontrados = produtos
                    .stream()
                    .filter(produtoModel -> produtoModel
                            .getNome()
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
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return ResponseEntity.status(200).body(produto);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/estoque/{id}")
    @Operation(summary = "Responsável por alterar um produto a partir do seu ID")
    public ResponseEntity<Produto> alterarProdutoPorId(@PathVariable int id, @RequestBody Produto produtoAlterado) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtoAlterado.setId(produto.getId());
                produtos.set(produtos.indexOf(produto), produtoAlterado);
                return ResponseEntity.status(200).body(produtoAlterado);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/estoque")
    @Operation(summary = "Responsável por alterar o nomde de um determinado produto")
    public ResponseEntity<Produto> alterarProdutoPorNome(@RequestParam String nomeProduto, @RequestBody Produto produtoAlterado) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                produtoAlterado.setId(produto.getId());
                produtos.set(produtos.indexOf(produto), produtoAlterado);
                return ResponseEntity.status(200).body(produtoAlterado);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/estoque/{id}")
    @Operation(summary = "Responsável por deletar um determinado produto por ID")
    public ResponseEntity<Produto> apagarProdutoPorId(@PathVariable int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtos.remove(produto);
                return ResponseEntity.status(204).build();
            }
        }
        return ResponseEntity.status(404).build();
    }

}
