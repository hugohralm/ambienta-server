package br.com.oversight.ambienta.repository;

import br.com.oversight.ambienta.model.Municipio;
import br.com.oversight.ambienta.model.enums.EnumUf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório de Municipio
 *
 * @see JpaRepository
 */
@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

   Optional<Municipio> findById(Integer id);

   List<Municipio> findByUfOrderByNome(EnumUf uf);

   Optional<Municipio> findByCodigoIbge(Integer codigoIbge);

}
