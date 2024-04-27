package sptech.school.projetovolt.api.imagemProduto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.imagemProduto.produto.repository.ImagemProdutoRepository;

@RestController
@RequestMapping("/imagemProdutos")
public class imagemProdutoController {

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;
}
