package br.com.oversight.ambienta.service;

import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.model.RespostaDenuncia;
import br.com.oversight.ambienta.model.dto.RespostaDenunciaTO;
import br.com.oversight.ambienta.repository.RespostaDenunciaRepository;
import br.com.oversight.ambienta.security.model.Usuario;
import br.com.oversight.ambienta.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RespostaDenunciaService {

   @Autowired
   private RespostaDenunciaRepository repository;

   @Autowired
   private DenunciaService denunciaService;

   @Autowired
   private UsuarioService usuarioService;

   public RespostaDenuncia createDTO(RespostaDenunciaTO respostaDenunciaTO) {
      Optional<Usuario> usuario = usuarioService.getUserWithAuthorities();
      if (usuario.isPresent()) {
         Denuncia denuncia = denunciaService.read(respostaDenunciaTO.getDenuncia().getId());
         RespostaDenuncia respostaDenuncia = new RespostaDenuncia();
         respostaDenuncia.setStatus(respostaDenunciaTO.getStatus());
         respostaDenuncia.setDescricao(respostaDenunciaTO.getDescricao());
         respostaDenuncia.setUsuario(usuario.get());
         respostaDenuncia.setDenuncia(denuncia);
         RespostaDenuncia rd = repository.saveAndFlush(respostaDenuncia);
         denuncia.setStatus(rd.getStatus());
         denunciaService.update(denuncia);
         return rd;
      } else {
         return null;
      }
   }

   public RespostaDenuncia create(RespostaDenuncia respostaDenuncia) {
      Optional<Usuario> usuario = usuarioService.getUserWithAuthorities();
      if (usuario.isPresent()) {
         Denuncia denuncia = denunciaService.read(respostaDenuncia.getDenuncia().getId());
         respostaDenuncia.setUsuario(usuario.get());
         respostaDenuncia.setDenuncia(denuncia);
         RespostaDenuncia rd = repository.saveAndFlush(respostaDenuncia);
         denuncia.setStatus(rd.getStatus());
         denunciaService.update(denuncia);
         return rd;
      } else {
         return null;
      }
   }

   public RespostaDenuncia read(Integer id) {
      return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Resposta denúncia %d", id)));
   }

   public void delete(Integer id) {
      repository.deleteById(id);
   }
}
