package sptech.school.projetovolt.service;

import sptech.school.projetovolt.model.ProdutoModel;

import java.util.List;
import java.util.Objects;

public class ProdutoService {
    public boolean existePorId(Integer id, List<ProdutoModel> produtoModels ){
        for (ProdutoModel produtoModel: produtoModels) {
            if (Objects.equals(id, produtoModel.getIdProduto())){
                return true;
            }
        }
        return false;
    }
}
