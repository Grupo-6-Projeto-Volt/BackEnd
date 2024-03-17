package sptech.school.projetovolt.service;

import sptech.school.projetovolt.model.ProdutoModel;

import java.util.List;

public class ProdutoService {
    public boolean existePorNome(String nome, List<ProdutoModel> produtoModels ){
        return produtoModels
                .stream()
                .anyMatch(produtoModel -> produtoModel.getNomeProduto().equalsIgnoreCase(nome));
    }
}
