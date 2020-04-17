package br.com.oversight.ambienta.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressTO {
   private String address;
   private String district;
   private String city;
   private String region;
   private String territory;
   private String postal;
   private String countryCode;

   @JsonProperty("endereco")
   public String getAddress() {
      return address;
   }

   @JsonProperty("bairro")
   public String getDistrict() {
      return district;
   }

   @JsonProperty("cidade")
   public String getCity() {
      return city;
   }

   @JsonProperty("estado")
   public String getRegion() {
      return region;
   }

   @JsonProperty("regiao")
   public String getTerritory() {
      return territory;
   }

   @JsonProperty("cep")
   public String getPostal() {
      return postal;
   }

   @JsonProperty("codigoPais")
   public String getCountryCode() {
      return countryCode;
   }

   @JsonProperty("Address")
   public void setAddress(String value) {
      this.address = value;
   }

   @JsonProperty("District")
   public void setDistrict(String value) {
      this.district = value;
   }

   @JsonProperty("City")
   public void setCity(String value) {
      this.city = value;
   }

   @JsonProperty("Region")
   public void setRegion(String value) {
      this.region = value;
   }

   @JsonProperty("Territory")
   public void setTerritory(String value) {
      this.territory = value;
   }

   @JsonProperty("Postal")
   public void setPostal(String value) {
      this.postal = value.replaceAll("\\D+", "");
   }

   @JsonProperty("CountryCode")
   public void setCountryCode(String value) {
      this.countryCode = value;
   }
}
