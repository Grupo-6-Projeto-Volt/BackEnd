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
import sptech.school.projetovolt.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/filtro")
    public ResponseEntity<List<ProdutoConsultaDTO>> filtrar(@RequestParam(required = false) Double preco,
                                                            @RequestParam(required = false) Integer desconto,
                                                            @RequestParam(required = false) Integer qtdEsdtoque){
        List<Produto> allProdutos = produtoRepository.findAll();

        return ResponseEntity.ok(ordena(allProdutos));
    }

    private List<ProdutoConsultaDTO> ordena(List<Produto> produtos){
        if(produtos.isEmpty()){
            return null;
        }else{
            Integer[] colunaId = new Integer[produtos.size()];
            Double[] colunaOrdenar = new Double[produtos.size()];
            for (int i = 0; i < produtos.size(); i++) {
                colunaOrdenar[i] = produtos.get(i).getPreco();
                colunaId[i] = produtos.get(i).getId();
            }


            Integer[] ids = ordenarListaProdutos(colunaOrdenar, colunaId, 0, colunaOrdenar.length-1);
            List<ProdutoConsultaDTO> produtosOrdenados = new ArrayList<>();

            for (int i = 0; i < ids.length; i++) {
                Optional<Produto> produtoDaVez = produtoRepository.findById(ids[i]);
                produtosOrdenados.add(ProdutoMapper.toDto(produtoDaVez.get()));
            }
            return produtosOrdenados;
        }
    }
    private static Integer[] ordenarListaProdutos(Number[] coluna, Integer[] ids, int inicio, int fim){
        int i = inicio;
        int j = fim;
        Double  pivo = coluna[(inicio + fim) / 2].doubleValue();
        while(i <= j){
            while(i < fim && coluna[i].doubleValue() < pivo){
                i++;
            }
            while (j > inicio && coluna[j].doubleValue() > pivo){
                j--;
            }
            if(i <= j){
                //faz a troca
                Double aux = coluna[i].doubleValue();
                Integer idAux = ids[i];
                coluna[i] = coluna[j];
                ids[i] = ids[j];
                coluna[j] = aux;
                ids[j] = idAux;
                //aumenta em i e decrementa em j
                i++;
                j--;

            }

            if(inicio < j){
                ordenarListaProdutos(coluna, ids, inicio, j);
            }
            if(i < fim){
                ordenarListaProdutos(coluna, ids, i, fim);
            }
        }
        return ids;
    }
}
