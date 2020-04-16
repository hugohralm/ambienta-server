package br.com.oversight.ambienta.service;

import br.com.oversight.ambienta.model.Municipio;
import br.com.oversight.ambienta.model.enums.EnumUf;
import br.com.oversight.ambienta.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class MunicipioService {

   @Autowired
   private MunicipioRepository municipioRepository;

   public List<Municipio> read() {
      return municipioRepository.findAll();
   }

   public List<Municipio> readByUF(EnumUf uf) {
      return municipioRepository.findByUfOrderByNome(uf);
   }

   public Municipio read(Integer id) {
      return municipioRepository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException(String.format("Municipio %d", id)));
   }

   public Municipio readByIbgeCode(Integer codigoIbge) {
      return municipioRepository.findByCodigoIbge(codigoIbge)
         .orElseThrow(() -> new EntityNotFoundException(String.format("Municipio %d", codigoIbge)));
   }
}
