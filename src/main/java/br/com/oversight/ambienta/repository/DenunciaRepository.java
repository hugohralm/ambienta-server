package br.com.oversight.ambienta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.oversight.ambienta.interfaces.IRepository;
import br.com.oversight.ambienta.model.Denuncia;

/**
 * Repositório de Denuncia
 *
 * @see JpaRepository
 *
 */
@Repository
public interface DenunciaRepository extends IRepository<Denuncia, Integer> {
    Page<Denuncia> findByTituloContainingIgnoreCaseOrderByTitulo(String titulo, Pageable pageable);

    Page<Denuncia> findByTituloContainingIgnoreCaseAndDenuncianteIsNullOrderByTitulo(String titulo, Pageable pageable);

    Page<Denuncia> findByDenuncianteIsNull(Pageable pageable);

    Optional<Denuncia> findByIdAndDenuncianteIsNull(Integer id);

    List<Denuncia> findByDenuncianteIsNull();
}
