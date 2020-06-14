package br.com.oversight.ambienta.service;

import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.model.Evidencia;
import br.com.oversight.ambienta.repository.EvidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EvidenciaService {

   @Autowired
   private DenunciaService denunciaService;

   @Autowired
   private EvidenciaRepository repository;

   @Autowired
   private CloudinaryService cloudinaryService;

   public Evidencia create(MultipartFile file, Integer id) {
      Map uploadResult = cloudinaryService.uploadFile(file);
      Evidencia evidencia = createEvidenciaObject(uploadResult);
      Denuncia denuncia = denunciaService.read(id);
      evidencia.setDenuncia(denuncia);
      return repository.saveAndFlush(evidencia);
   }

   private Evidencia createEvidenciaObject(Map uploadResult) {
      Evidencia evidencia = new Evidencia();
      evidencia.setIdPublico(uploadResult.get("public_id").toString());
      evidencia.setAssinatura(uploadResult.get("signature").toString());
      evidencia.setFormato(uploadResult.get("format").toString());
      evidencia.setTipoArquivo(uploadResult.get("resource_type").toString());
      String url = uploadResult.get("url").toString();
      if (url.contains("http:")) {
         url = url.replace("http:", "https:");
      }
      evidencia.setUrl(url);
      return evidencia;
   }

   public List<Evidencia> read() {
      return repository.findAll();
   }

   public Evidencia read(Integer id) {
      return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Evidencia %d", id)));
   }

   public void update(Evidencia evidencia) {
      repository.save(evidencia);
   }

   public void delete(Integer id) {
      repository.deleteById(id);
   }
}
