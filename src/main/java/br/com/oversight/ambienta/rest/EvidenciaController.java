package br.com.oversight.ambienta.rest;

import br.com.oversight.ambienta.model.Evidencia;
import br.com.oversight.ambienta.service.EvidenciaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping(value = "/api/evidencias")
public class EvidenciaController extends DefaultController {

   @Autowired
   private EvidenciaService evidenciaService;

   @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.CREATED)
   public Evidencia uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("denuncia_id") Integer id) {
      return evidenciaService.create(file, id);
   }
}
