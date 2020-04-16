package br.com.oversight.ambienta.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Interface genérica para camada de repositório
 *
 * @param <T>
 * @param <N>
 */
@NoRepositoryBean
public interface IRepository<T, N> extends JpaRepository<T, N>, JpaSpecificationExecutor<T> {

   Optional<T> findById(N id);
}
