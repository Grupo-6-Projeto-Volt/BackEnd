package sptech.school.projetovolt.api.imagemProduto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.imagemProduto.repository.ImagemProdutoRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/imagemProdutos")
public class imagemProdutoController {

    private final ImagemProdutoRepository imagemProdutoRepository;
}
