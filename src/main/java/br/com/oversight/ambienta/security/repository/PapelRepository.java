package br.com.oversight.ambienta.security.repository;

import br.com.oversight.ambienta.security.model.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Papel} entity.
 */
public interface PapelRepository extends JpaRepository<Papel, String> {
}
