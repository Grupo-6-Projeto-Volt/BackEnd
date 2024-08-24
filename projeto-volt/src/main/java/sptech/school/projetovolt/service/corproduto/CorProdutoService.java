package sptech.school.projetovolt.service.corproduto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.corProduto.CorProduto;
import sptech.school.projetovolt.entity.corProduto.repository.CorProdutoRepository;
import sptech.school.projetovolt.entity.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorProdutoService {

    private final CorProdutoRepository corProdutoRepository;

    public List<CorProduto> listarCores() {
        return corProdutoRepository.findAll();
    }

    public CorProduto buscarCorPorId(Integer id) {
        return corProdutoRepository
               .findById(id)
               .orElseThrow(() -> new NotFoundException("Cor Id " + id));
    }

    public CorProduto buscarCorPorNome(String nome) {
        return corProdutoRepository
               .findByNome(nome)
               .orElseThrow(() -> new NotFoundException("Cor " + nome));
    }

    public CorProduto buscarCorPorHex(String hex) {
        return corProdutoRepository
               .findByHexId(hex)
               .orElseThrow(() -> new NotFoundException("Cor Hex " + hex));
    }

    public CorProduto criarCor(CorProduto novaCor) {
        return corProdutoRepository.save(novaCor);
    }

    public void deletarCorPorId(Integer id) {
        if (!corProdutoRepository.existsById(id)) {
            throw new NotFoundException("Cor " + id);
        }
        corProdutoRepository.deleteById(id);
    }


}
