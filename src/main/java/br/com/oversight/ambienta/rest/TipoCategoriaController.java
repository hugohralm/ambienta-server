package br.com.oversight.ambienta.rest;

import br.com.oversight.ambienta.interfaces.IController;
import br.com.oversight.ambienta.model.TipoCategoria;
import br.com.oversight.ambienta.service.TipoCategoriaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/tipos-categoria")
public class TipoCategoriaController extends DefaultController implements IController<TipoCategoria, Integer> {

   @Autowired
   private TipoCategoriaService service;

   /**
    * Armazena um {@link TipoCategoria} no sistema
    *
    * @param tipoCategoria Representação do recurso
    * @return ResponseEntity tipoCategoria
    */
   @Override
   @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.CREATED)
   @ApiOperation(value = "Armazena o registro do tipo categoria.")
   public ResponseEntity<?> create(@Valid @RequestBody TipoCategoria tipoCategoria) {
      log.trace("Criando tipo categoria {}", tipoCategoria);
      tipoCategoria = service.create(tipoCategoria);
      HttpHeaders responseHeaders = getHttpHeaders(tipoCategoria.getId());
      return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(tipoCategoria);
   }

   /**
    * Retorna um {@link TipoCategoria} pelo identificador informado
    *
    * @param id Identificador do recurso
    * @return
    */
   @Override
   @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Retorna o tipo categoria pelo seu Identificador.")
   public ResponseEntity<?> read(@PathVariable Integer id) {
      log.trace("Buscando tipo categoria por identificador {}", id);
      TipoCategoria tipoCategoria = service.read(id);
      HttpHeaders responseHeaders = getHttpHeaders(tipoCategoria.getId());
      return ResponseEntity.ok().headers(responseHeaders).body(tipoCategoria);
   }

   /**
    * Pesquisa um registro de {@link TipoCategoria} baseado numa descrição
    *
    * @param nome Campo a ser pesquisado
    * @param page      Página inicial
    * @param size      Tamanho da paginação
    * @return
    */
   @Override
   @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Listar o tipo categoria em ordem alfabética.")
   public ResponseEntity<?> read(@RequestParam(required = false) String nome,
                                 @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
      Page<TipoCategoria> list = service.read(nome, PageRequest.of(page, size));
      ResponseHeaderPaginable responseHeaderPaginable = new ResponseHeaderPaginable(page, list);
      responseHeaderPaginable.invoke();
      HttpStatus status = responseHeaderPaginable.getStatus();
      return ResponseEntity.status(status).header(CONTENT_RANGE_HEADER, responseHeaderPaginable.responsePageRange())
         .body(list);
   }

   /**
    * Pesquisa todos os registros de {@link TipoCategoria}
    *
    * @return
    */
   @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Listar todos os tipos de categoria em ordem alfabética.")
   public ResponseEntity<?> read() {
      List<TipoCategoria> list = service.read();
      return ResponseEntity.ok().body(list);
   }

   /**
    * Atualização registro de um {@link TipoCategoria}
    *
    * @param id            Identificador do recurso
    * @param tipoCategoria Representação do recurso
    * @return
    */
   @Override
   @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Altera, restritamente, todo o registro do tipo de categoria.")
   public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody TipoCategoria tipoCategoria) {
      log.trace("Alterando tipo categoria {}", tipoCategoria);
      tipoCategoria.setId(id);
      service.update(tipoCategoria);
      HttpHeaders responseHeaders = getHttpHeaders(null);
      return ResponseEntity.noContent().headers(responseHeaders).build();
   }

   /**
    * Remove um registro de {@link TipoCategoria}
    *
    * @param id Identificador da {@link TipoCategoria}
    * @return
    */
   @DeleteMapping(path = "/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   @ApiOperation(value = "Remove o registro do tipo de categoria.")
   public ResponseEntity<?> delete(@PathVariable Integer id) {
      log.trace("Removendo tipo categoria {}", id);
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

   @Override
   @RequestMapping(method = RequestMethod.OPTIONS)
   public ResponseEntity<?> options() {
      return ResponseEntity.ok()
         .allow(HttpMethod.POST, HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS).build();
   }
}
