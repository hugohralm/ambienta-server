package br.com.oversight.ambienta.repository;

import br.com.oversight.ambienta.interfaces.IRepository;
import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.model.RespostaDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de Denuncia
 *
 * @see JpaRepository
 */
@Repository
public interface RespostaDenunciaRepository extends IRepository<RespostaDenuncia, Integer> {
   List<RespostaDenuncia> findAllByDenunciaOrderByDataCadastroAsc(Denuncia denuncia);
}
