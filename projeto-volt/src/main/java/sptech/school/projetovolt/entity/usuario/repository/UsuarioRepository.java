package sptech.school.projetovolt.entity.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
