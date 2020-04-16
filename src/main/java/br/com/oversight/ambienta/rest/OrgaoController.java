package br.com.oversight.ambienta.rest;

import br.com.oversight.ambienta.interfaces.IController;
import br.com.oversight.ambienta.model.Orgao;
import br.com.oversight.ambienta.service.OrgaoService;
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
@RequestMapping(value = "/api/orgaos")
public class OrgaoController extends DefaultController implements IController<Orgao, Integer> {

   @Autowired
   private OrgaoService service;

   /**
    * Armazena um {@link Orgao} no sistema
    *
    * @param orgao Representação do recurso
    * @return ResponseEntity tipoCategoria
    */
   @Override
   @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.CREATED)
   @ApiOperation(value = "Armazena o registro do órgão.")
   public ResponseEntity<?> create(@Valid @RequestBody Orgao orgao) {
      log.trace("Criando órgão {}", orgao);
      orgao = service.create(orgao);
      HttpHeaders responseHeaders = getHttpHeaders(orgao.getId());
      return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(orgao);
   }

   /**
    * Retorna um {@link Orgao} pelo identificador informado
    *
    * @param id Identificador do recurso
    * @return
    */
   @Override
   @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Retorna o órgão pelo seu Identificador.")
   public ResponseEntity<?> read(@PathVariable Integer id) {
      log.trace("Buscando tipo categoria por identificador {}", id);
      Orgao orgao = service.read(id);
      HttpHeaders responseHeaders = getHttpHeaders(orgao.getId());
      return ResponseEntity.ok().headers(responseHeaders).body(orgao);
   }

   /**
    * Pesquisa um registro de {@link Orgao} baseado numa descrição
    *
    * @param nome Campo a ser pesquisado
    * @param page      Página inicial
    * @param size      Tamanho da paginação
    * @return
    */
   @Override
   @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Listar o órgão em ordem alfabética.")
   public ResponseEntity<?> read(@RequestParam(required = false) String nome,
                                 @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
      Page<Orgao> list = service.read(nome, PageRequest.of(page, size));
      ResponseHeaderPaginable responseHeaderPaginable = new ResponseHeaderPaginable(page, list);
      responseHeaderPaginable.invoke();
      HttpStatus status = responseHeaderPaginable.getStatus();
      return ResponseEntity.status(status).header(CONTENT_RANGE_HEADER, responseHeaderPaginable.responsePageRange())
         .body(list);
   }

   /**
    * Pesquisa todos os registros de {@link Orgao}
    *
    * @return
    */
   @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Listar todos os órgãos em ordem alfabética.")
   public ResponseEntity<?> read() {
      List<Orgao> list = service.read();
      return ResponseEntity.ok().body(list);
   }

   /**
    * Atualização registro de um {@link Orgao}
    *
    * @param id            Identificador do recurso
    * @param orgao Representação do recurso
    * @return
    */
   @Override
   @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Altera, restritamente, todo o registro do órgão.")
   public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody Orgao orgao) {
      log.trace("Alterando órgão {}", orgao);
      orgao.setId(id);
      service.update(orgao);
      HttpHeaders responseHeaders = getHttpHeaders(null);
      return ResponseEntity.noContent().headers(responseHeaders).build();
   }

   /**
    * Remove um registro de {@link Orgao}
    *
    * @param id Identificador da {@link Orgao}
    * @return
    */
   @DeleteMapping(path = "/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   @ApiOperation(value = "Remove o registro do órgão.")
   public ResponseEntity<?> delete(@PathVariable Integer id) {
      log.trace("Removendo órgão {}", id);
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
