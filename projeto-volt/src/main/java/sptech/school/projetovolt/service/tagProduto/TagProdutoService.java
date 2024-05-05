package sptech.school.projetovolt.service.tagProduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.ConflictException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.entity.tagProduto.repository.TagProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagProdutoService {

    private final TagProdutoRepository tagProdutoRepository;

    public TagProduto criarTag (TagProduto novaTag) {
        if (tagProdutoRepository.existsByTag(novaTag.getTag())) {
            throw new ConflictException("TagProduto " + novaTag.getTag());
        }
        return tagProdutoRepository.save(novaTag);
    }

    public List<TagProduto> listarTags () {
        return tagProdutoRepository.findAll();
    }

    public TagProduto buscarTagPorId (Integer id) {
        Optional<TagProduto> tagEncontrada = tagProdutoRepository.findById(id);
        return tagEncontrada.orElseThrow(() -> new NotFoundException("TagProduto " + id));
    }

    public TagProduto atualizarTag (Integer id, String tag) {
        TagProduto tagEncontrada = buscarTagPorId(id);

        if (tagProdutoRepository.existsByTag(tag)) {throw new ConflictException("TagProduto " + id);}

        tagEncontrada.setTag(tag);
        return tagProdutoRepository.save(tagEncontrada);
    }

    public void deletarTagPorId (Integer id) {
        TagProduto tagEncontrada = buscarTagPorId(id);
        tagProdutoRepository.deleteById(id);
    }


}
