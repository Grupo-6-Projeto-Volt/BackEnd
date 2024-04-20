package sptech.school.projetovolt.entity.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.projetovolt.entity.login.Login;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<Login, UUID> {

    Optional<Login> findByEmail(String email);
}
