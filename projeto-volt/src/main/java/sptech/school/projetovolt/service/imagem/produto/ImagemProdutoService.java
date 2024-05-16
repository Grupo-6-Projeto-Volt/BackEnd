package sptech.school.projetovolt.service.imagem.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.imagem.produto.repository.ImagemProdutoRepository;

@Service
@RequiredArgsConstructor
public class ImagemProdutoService {

    private final ImagemProdutoRepository imagemProdutoRepository;

}
