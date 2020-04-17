package br.com.oversight.ambienta.security.repository;

import br.com.oversight.ambienta.security.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

   @EntityGraph(attributePaths = "papeis")
   Optional<Usuario> findOneWithPapelByCpf(String cpf);

   @EntityGraph(attributePaths = "papeis")
   Optional<Usuario> findOneWithPapelByEmailIgnoreCase(String email);

   @EntityGraph(attributePaths = "papeis")
   List<Usuario> findAllByOrderByNomeAsc();
}
