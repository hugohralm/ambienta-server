package br.com.oversight.ambienta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.oversight.ambienta.model.Municipio;
import br.com.oversight.ambienta.model.enums.EnumUf;
import br.com.oversight.ambienta.service.MunicipioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@Api(value = "Operações de CRUD em Municipio")
@RequestMapping(value = "/api/municipios", path = "/api/municipios")
public class MunicipioController extends DefaultController {

    @Autowired
    private MunicipioService municipioService;

    /**
     * Retorna um {@link Municipio} pelo identificador informado
     *
     * @param id Identificador do recurso
     * @return
     */
    @ApiOperation(value = "Retorna o municipio pelo seu Identificador.")
    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> read(@PathVariable Integer id) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.debug(String.format("Método: %s | Parâmetro: %d", methodName, id));
        log.trace("Buscando municipio por identificador {}", id);
        Municipio municipio = municipioService.read(id);
        HttpHeaders responseHeaders = getHttpHeaders(municipio.getId());
        return ResponseEntity.ok().headers(responseHeaders).body(municipio);
    }

    /**
     * Retorna um {@link Municipio} pelo identificador informado
     *
     * @param ibge Identificador do recurso
     * @return
     */
    @GetMapping(path = "ibge/{ibge}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna o municipio pelo seu código IBGE.")
    public ResponseEntity<?> readByIbgeCode(@PathVariable Integer codigoIbge) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.debug(String.format("Método: %s | Parâmetro: %d", methodName, codigoIbge));
        log.trace("Buscando municipio por identificador {}", codigoIbge);
        Municipio municipio = municipioService.readByIbgeCode(codigoIbge);
        HttpHeaders responseHeaders = getHttpHeaders(municipio.getId());
        return ResponseEntity.ok().headers(responseHeaders).body(municipio);
    }

    /**
     * Pesquisar os registros de {@link Municipio} por UF
     *
     * @param uf Campo a ser pesquisado
     * @return
     */
    @ApiOperation(value = "Listar todos os municipios por uf em ordem alfabética.")
    @GetMapping(path = "uf/{uf}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> readByUf(@PathVariable String uf) {
        EnumUf enumUf = EnumUf.valueOf(uf);
        List<Municipio> list = municipioService.readByUF(enumUf);
        ResponseHeader responseHeader = new ResponseHeader(list);
        responseHeader.invoke();
        HttpStatus status = responseHeader.getStatus();
        return ResponseEntity.status(status).body(list);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.OPTIONS).build();
    }
}
