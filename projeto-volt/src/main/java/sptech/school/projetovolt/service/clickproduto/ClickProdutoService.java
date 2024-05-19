package sptech.school.projetovolt.service.clickproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.clickProduto.repository.ClickProdutoRepository;

@Service
@RequiredArgsConstructor
public class ClickProdutoService {
    private final ClickProdutoRepository repository;

}
