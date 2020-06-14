package br.com.oversight.ambienta.service;

import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.model.RespostaDenuncia;
import br.com.oversight.ambienta.repository.RespostaDenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RespostaDenunciaService {

   @Autowired
   private RespostaDenunciaRepository repository;

   @Autowired
   private DenunciaService denunciaService;

   public RespostaDenuncia create(RespostaDenuncia respostaDenuncia) {
      RespostaDenuncia rd = repository.saveAndFlush(respostaDenuncia);
      Denuncia denuncia = denunciaService.read(rd.getDenuncia().getId());
      denuncia.setStatus(rd.getStatus());
      denunciaService.update(denuncia);
      return rd;
   }

   public RespostaDenuncia read(Integer id) {
      return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Resposta denúncia %d", id)));
   }

   public void delete(Integer id) {
      repository.deleteById(id);
   }
}
