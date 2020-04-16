package br.com.oversight.ambienta.repository;

import br.com.oversight.ambienta.interfaces.IRepository;
import br.com.oversight.ambienta.model.Denuncia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório de Denuncia
 *
 * @see JpaRepository
 */
@Repository
public interface DenunciaRepository extends IRepository<Denuncia, Integer> {
   Page<Denuncia> findByTituloContainingIgnoreCaseOrderByTitulo(String titulo, Pageable pageable);

   Optional<Denuncia> findByCodigoAcompanhamento(String codigoAcompanhamento);
}
