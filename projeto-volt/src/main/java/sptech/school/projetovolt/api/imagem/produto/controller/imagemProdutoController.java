package sptech.school.projetovolt.api.imagem.produto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.imagem.produto.ImagemProduto;
import sptech.school.projetovolt.service.imagem.produto.ImagemProdutoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/imagemProdutos")
public class imagemProdutoController {

    private final ImagemProdutoService imagemProdutoService;

    public void adicionarImagem(ImagemProduto novaImagem) {

    }
}
