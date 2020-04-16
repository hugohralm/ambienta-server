package br.com.oversight.ambienta.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum EnumUf {

   AC("Acre", 12),
   AL("Alagoas", 27),
   AP("Amapá", 16),
   AM("Amazonas", 13),
   BA("Bahia", 29),
   CE("Ceará", 23),
   DF("Distrito Federal", 53),
   ES("Espírito Santo", 32),
   GO("Goiás", 52),
   MA("Maranhão", 21),
   MT("Mato Grosso", 51),
   MS("Mato Grosso do Sul", 50),
   MG("Minas Gerais", 31),
   PA("Pará", 15),
   PB("Paraíba", 25),
   PE("Pernambuco", 26),
   PR("Paraná", 41),
   PI("Piauí", 22),
   RJ("Rio De Janeiro", 33),
   RN("Rio Grande do Norte", 24),
   RS("Rio Grande do Sul", 43),
   RO("Rondônia", 11),
   RR("Roraima", 14),
   SC("Santa Catarina", 42),
   SE("Sergipe", 28),
   SP("São Paulo", 35),
   TO("Tocantins", 17);

   @Getter
   private final String descricao;

   @Getter
   private final int codigoIbge;

   public static EnumUf valueOf(Integer codigoIbge) {
      Optional<EnumUf> opt = Arrays.stream(values()).filter(uf -> uf.codigoIbge == codigoIbge).findFirst();
      return opt.isPresent() ? opt.get() : null;
   }

}
