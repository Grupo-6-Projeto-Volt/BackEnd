package sptech.school.projetovolt.service;

import sptech.school.projetovolt.entity.produto.Produto;


import java.util.List;

public class ProdutoService {
    public boolean existePorNome(String nome, List<Produto> produtoModels ){
        return produtoModels
                .stream()
                .anyMatch(produtoModel -> produtoModel.getNome().equalsIgnoreCase(nome));
    }
}
