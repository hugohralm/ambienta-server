package br.com.oversight.ambienta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.model.TipoCategoria;
import br.com.oversight.ambienta.service.DenunciaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/api/denuncias")
public class DenunciaController extends DefaultController {

    @Autowired
    private DenunciaService service;

    /**
     * Armazena um {@link Denuncia} no sistema
     *
     * @param denuncia Representação do recurso
     * @return ResponseEntity denuncia
     */
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Armazena o registro da denúncia.")
    public ResponseEntity<?> create(@Valid @RequestBody Denuncia denuncia) {
        log.trace("Criando denúncia {}", denuncia);
        denuncia = service.create(denuncia);
        HttpHeaders responseHeaders = getHttpHeaders(denuncia.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(denuncia);
    }

    /**
     * Retorna um {@link Denuncia} pelo identificador informado
     *
     * @param id Identificador do recurso
     * @return
     */
    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna a denúncia pelo seu Identificador.")
    public ResponseEntity<?> read(@PathVariable Integer id) {
        log.trace("Buscando a denúncia por identificador {}", id);
        Denuncia denuncia = service.read(id);
        HttpHeaders responseHeaders = getHttpHeaders(denuncia.getId());
        return ResponseEntity.ok().headers(responseHeaders).body(denuncia);
    }
    
    /**
     * Retorna um {@link Denuncia} pelo codigoAcompanhamento informado
     *
     * @param codigoAcompanhamento Código de acompanhamento do recurso
     * @return
     */
    @GetMapping(path = "/acompanhar/{codigoAcompanhamento}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna a denúncia pelo seu código de acompanhamento.")
    public ResponseEntity<?> read(@PathVariable String codigoAcompanhamento) {
        log.trace("Buscando a denúncia pelo seu código de acompanhamento {}", codigoAcompanhamento);
        Denuncia denuncia = service.readByCodigoAcompanhamento(codigoAcompanhamento);
        HttpHeaders responseHeaders = getHttpHeaders(denuncia.getId());
        return ResponseEntity.ok().headers(responseHeaders).body(denuncia);
    }

    /**
     * Pesquisa um registro de {@link Denuncia} baseado numa descrição
     *
     * @param descricao Campo a ser pesquisado
     * @param page      Página inicial
     * @param size      Tamanho da paginação
     * @return
     */
    @GetMapping(path = "/page", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar a denúncia em ordem alfabética.")
    public ResponseEntity<?> read(@RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        Page<Denuncia> list = service.read(nome, PageRequest.of(page, size));
        ResponseHeaderPaginable responseHeaderPaginable = new ResponseHeaderPaginable(page, list);
        responseHeaderPaginable.invoke();
        HttpStatus status = responseHeaderPaginable.getStatus();
        return ResponseEntity.status(status).header(CONTENT_RANGE_HEADER, responseHeaderPaginable.responsePageRange())
                .body(list);
    }

    /**
     * Pesquisa todos os registros de {@link Denuncia}
     *
     * @return
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar todas as denúncias em ordem alfabética.")
    public ResponseEntity<?> read() {
        List<Denuncia> list = service.read();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Atualização registro de um {@link TipoCategoria}
     *
     * @param id            Identificador do recurso
     * @param tipoCategoria Representação do recurso
     * @return
     */
    @PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera, restritamente, todo o registro da denúncia.")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody Denuncia denuncia) {
        log.trace("Alterando a denúncia {}", denuncia);
        denuncia.setId(id);
        service.update(denuncia);
        HttpHeaders responseHeaders = getHttpHeaders(null);
        return ResponseEntity.noContent().headers(responseHeaders).build();
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok()
                .allow(HttpMethod.POST, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
