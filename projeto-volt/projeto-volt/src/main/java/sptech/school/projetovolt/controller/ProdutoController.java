package sptech.school.projetovolt.controller;


import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.model.ProdutoModel;
import sptech.school.projetovolt.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<ProdutoModel> produtos = new ArrayList<>();
    private ProdutoService produtoService = new ProdutoService();
    private Integer id = 0;

    @PostMapping("/estoque")
    public ResponseEntity<ProdutoModel> cadastrarProduto(@RequestBody ProdutoModel produtoNovo) {
        if (!Objects.isNull(produtoNovo)) {
            if (!produtoService.existePorId(produtoNovo.getIdProduto(),produtos)){
                produtoNovo.setIdProduto(++id);
                produtos.add(produtoNovo);
                return ResponseEntity.status(201).body(produtoNovo);
            }
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/loja")
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos(@RequestParam(required = false) String textoBusca) {
        if (textoBusca != null) {
            return ResponseEntity.status(200).body(produtos
                    .stream()
                    .filter(produtoModel -> produtoModel
                            .getNomeProduto()
                            .contains(textoBusca))
                    .toList());
        }
        return ResponseEntity.status(200).body(produtos);
    }

    @GetMapping("/loja/{id}")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable int id) {
        for (ProdutoModel produto : produtos) {
            if (produto.getIdProduto() == id) {
                return ResponseEntity.status(200).body(produto);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/estoque/{id}")
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
    public ResponseEntity<ProdutoModel> apagarProdutoPorId(@PathVariable int id) {
        for (ProdutoModel produto : produtos) {
            if (produto.getIdProduto() == id) {
                produtos.remove(produto);
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(404).build();
    }

}
