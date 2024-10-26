package sptech.school.projetovolt.entity.categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.categoria.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);
    Optional<Categoria> findByNome(String nome);
}