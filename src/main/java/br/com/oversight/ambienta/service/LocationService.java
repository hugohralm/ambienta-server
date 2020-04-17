package br.com.oversight.ambienta.service;

import br.com.oversight.ambienta.model.Municipio;
import br.com.oversight.ambienta.model.dto.EnderecoTO;
import br.com.oversight.ambienta.model.dto.GeocodeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LocationService {

   @Autowired
   private MunicipioService municipioService;

   public EnderecoTO getEnderecoTO(String cep) {
      String uri = "http://viacep.com.br/ws/{cep}/json/";
      if (cep.length() < 8) {
         cep = String.format("%-8s", cep).replace(' ', '0');
      }
      Map<String, String> params = new HashMap<>();
      params.put("cep", cep);
      return getForObject(uri, EnderecoTO.class, params);
   }

   public GeocodeTO getGeocodeTO(double lng, double lat) {
      String uri = "https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?location={lng},{lat}&f=json";
      Map<String, Double> params = new HashMap<>();
      params.put("lng", lng);
      params.put("lat", lat);
      return getForObject(uri, GeocodeTO.class, params);
   }

   public Municipio getMunicipio(double lng, double lat) {
      GeocodeTO geocodeTO = getGeocodeTO(lng, lat);
      EnderecoTO enderecoTO = getEnderecoTO(geocodeTO.getAddress().getPostal());
      return municipioService.readByIbgeCode(enderecoTO.getIbge());
   }

   private <T> T getForObject(String url,
                      Class<T> responseType,
                      java.util.Map<String, ?> uriVariables) {
      RestTemplate restTemplate = new RestTemplate();
      return restTemplate.getForObject(url, responseType, uriVariables);
   }
}
