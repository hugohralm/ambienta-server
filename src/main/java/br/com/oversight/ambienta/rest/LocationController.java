package br.com.oversight.ambienta.rest;

import br.com.oversight.ambienta.model.dto.AddressTO;
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
@RequestMapping(value = "/api/localizacoes")
public class LocationController {

   @Autowired
   private LocationService locationService;

   @GetMapping(value = "/endereco/{cep}")
   public ResponseEntity<EnderecoTO> read(@PathVariable(name = "cep") String cep) {
      return new ResponseEntity<>(locationService.getEndereco(cep), HttpStatus.OK);
   }

   @GetMapping(value = "/geocodeReverso/{lat},{lng}")
   public ResponseEntity<AddressTO> read(@PathVariable(name = "lat") double lat, @PathVariable(name = "lng") double lng) {
      return new ResponseEntity<>(locationService.getReverseGeocode(lng, lat), HttpStatus.OK);
   }
}
