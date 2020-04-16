package br.com.oversight.ambienta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.oversight.ambienta.model.Municipio;
import br.com.oversight.ambienta.model.enums.EnumUf;

/**
 * Repositório de Municipio
 *
 * @see JpaRepository
 *
 */
@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    Optional<Municipio> findById(Integer id);

    Page<Municipio> findAllByOrderByNome(Pageable pageable);

    List<Municipio> findByUfOrderByNome(EnumUf uf);

    @Query(value = "SELECT m FROM Municipio m "
            + "WHERE UPPER(m.nome) LIKE UPPER(CONCAT('%',:descricao,'%')) or UPPER(m.uf) LIKE UPPER(CONCAT('%',:descricao,'%')) "
            + " or CAST(m.codigoIbge as string) = :descricao or UPPER(CAST(m.descentralizado as string)) = UPPER(:descricao) ORDER BY nome ASC")
    Page<Municipio> page(@Param("descricao") String nome, @Param("pageable") Pageable pageable);

    Optional<Municipio> findByCodigoIbge(Integer codigoIbge);

}
