package sptech.school.projetovolt.api.produto.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.dto.ProdutoAlteracaoDto;
import sptech.school.projetovolt.entity.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.entity.produto.dto.ProdutoCriacaoDTO;
import sptech.school.projetovolt.entity.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    private final ProdutoService produtoService = new ProdutoService();

    @PostMapping("/estoque")
    @Operation(summary = "Responsável por cadastrar um produto no estoque")
    public ResponseEntity<ProdutoConsultaDTO> cadastrarProduto(@RequestBody ProdutoCriacaoDTO produtoNovo) {
                Produto produtoSalvo = produtoRepository.save(ProdutoMapper.toEntity(produtoNovo));
                return ResponseEntity.status(201).body(ProdutoMapper.toDto(produtoSalvo));
    }

    @GetMapping("/loja")
    @Operation(summary = "Responsável por listar todos os produtos da Loja")
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
    @Operation(summary = "Responsável por buscar um produto por ID")
    public ResponseEntity<ProdutoConsultaDTO> buscarProdutoPorId(@PathVariable int id) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);
        if(produtoEncontrado.isPresent()){
            return ResponseEntity.status(200).body(ProdutoMapper.toDto(produtoEncontrado.get()));
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/estoque/{id}")
    @Operation(summary = "Responsável por alterar um produto a partir do seu ID")
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
    @Operation(summary = "Responsável por deletar um determinado produto por ID")
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
