package br.com.oversight.ambienta.rest;

import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.model.RespostaDenuncia;
import br.com.oversight.ambienta.service.RespostaDenunciaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/api/respostas-denuncias")
public class RespostaDenunciaController extends DefaultController {

   @Autowired
   private RespostaDenunciaService service;

   /**
    * Armazena um {@link Denuncia} no sistema
    *
    * @param respostaDenuncia Representação do recurso
    * @return ResponseEntity
    */
   @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.CREATED)
   @ApiOperation(value = "Armazena o registro da resposta denúncia.")
   public ResponseEntity<?> create(@RequestBody RespostaDenuncia respostaDenuncia) {
      log.trace("Criando resposta denúncia {}", respostaDenuncia);
      respostaDenuncia = service.create(respostaDenuncia);
      HttpHeaders responseHeaders = getHttpHeaders(respostaDenuncia.getId());
      return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(respostaDenuncia);
   }

   /**
    * Retorna um {@link RespostaDenuncia} pelo identificador informado
    *
    * @param id Identificador da {@link RespostaDenuncia}
    * @return ResponseEntity
    */
   @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Retorna a resposta da denúncia pelo seu Identificador.")
   public ResponseEntity<?> read(@PathVariable Integer id) {
      log.trace("Buscando a denúncia por identificador {}", id);
      RespostaDenuncia respostaDenuncia = service.read(id);
      HttpHeaders responseHeaders = getHttpHeaders(respostaDenuncia.getId());
      return ResponseEntity.ok().headers(responseHeaders).body(respostaDenuncia);
   }

   /**
    * Remove um registro de {@link RespostaDenuncia}
    *
    * @param id Identificador da {@link RespostaDenuncia}
    * @return ResponseEntity
    */
   @DeleteMapping(path = "/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   @ApiOperation(value = "Remove o registro de resposta denúncia.")
   public ResponseEntity<?> delete(@PathVariable Integer id) {
      log.trace("Removendo resposta denúncia {}", id);
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

   @RequestMapping(method = RequestMethod.OPTIONS)
   public ResponseEntity<?> options() {
      return ResponseEntity.ok()
         .allow(HttpMethod.POST, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
   }
}
