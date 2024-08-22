package sptech.school.projetovolt.entity.categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.categoria.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}