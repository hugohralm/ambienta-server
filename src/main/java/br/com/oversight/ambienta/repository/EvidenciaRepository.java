package br.com.oversight.ambienta.repository;

import br.com.oversight.ambienta.interfaces.IRepository;
import br.com.oversight.ambienta.model.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de Evidencia
 *
 * @see JpaRepository
 */
@Repository
public interface EvidenciaRepository extends IRepository<Evidencia, Integer> {
   List<Evidencia> findAllByDenunciaIdOrderById(Integer denunciaId);
}
