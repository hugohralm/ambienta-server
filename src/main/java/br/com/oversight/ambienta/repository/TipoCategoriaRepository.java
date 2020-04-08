package br.com.oversight.ambienta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.oversight.ambienta.interfaces.IRepository;
import br.com.oversight.ambienta.model.TipoCategoria;

/**
 * Repositório de TipoCategoria
 *
 * @see JpaRepository
 *
 */
@Repository
public interface TipoCategoriaRepository extends IRepository<TipoCategoria, Integer> {
    Page<TipoCategoria> findByNomeContainingIgnoreCaseOrderByNome(String nome, Pageable pageable);
}
