package br.com.oversight.ambienta.rest;

import br.com.oversight.ambienta.model.Municipio;
import br.com.oversight.ambienta.model.dto.EnderecoTO;
import br.com.oversight.ambienta.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/locations")
public class LocationController {

   @Autowired
   private LocationService locationService;

   @GetMapping(value = "/{cep}")
   public ResponseEntity<EnderecoTO> read(@PathVariable(name = "cep") String cep) {
      return new ResponseEntity<>(locationService.getEnderecoTO(cep), HttpStatus.OK);
   }

   @GetMapping
   public ResponseEntity<Municipio> read() {
      return new ResponseEntity<>(locationService.getMunicipio(-49.294377, -16.741160), HttpStatus.OK);
   }
}
