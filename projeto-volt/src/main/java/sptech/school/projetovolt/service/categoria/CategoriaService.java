package sptech.school.projetovolt.service.categoria;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.categoria.Categoria;
import sptech.school.projetovolt.entity.categoria.repository.CategoriaRepository;
import sptech.school.projetovolt.entity.exception.ConflictException;
import sptech.school.projetovolt.entity.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria cadastrarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria alterarCategoria(Integer id, String nome) {
        Categoria categoriaEncontrada = buscarCategoriaPorId(id);

        if (categoriaRepository.existsByNome(nome)) {
            throw new ConflictException("Categoria");
        }

        categoriaEncontrada.setNome(nome);
        return categoriaRepository.save(categoriaEncontrada);
    }

    public void deletarCategoria(Integer id) {
        buscarCategoriaPorId(id);
        categoriaRepository.deleteById(id);
    }

    public Categoria buscarCategoriaPorId(Integer id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        return categoriaOpt.orElseThrow(() -> new NotFoundException("Categoria"));
    }

    public List<Categoria> buscarCategoriasPorNomeContendo(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Categoria buscarCategoriasPorNome(String nome) {
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findByNome(nome);
        return categoriaEncontrada.orElseThrow(() -> new NotFoundException("Categoria " + nome));
    }
}
