package sptech.school.projetovolt.entity.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.usuario.Usuario;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
