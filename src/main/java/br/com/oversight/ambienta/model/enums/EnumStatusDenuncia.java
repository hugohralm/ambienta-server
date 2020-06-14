package br.com.oversight.ambienta.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumStatusDenuncia {

   AGUARDANDO_ANALISE("Aguardando Análise"), //Aguardando análise do órgão gestor
   EM_APURACAO("Em apuração"), //Denúncia está em análise
   CONCLUIDA("Concluída"), //Denúncia tratada
   DESQUALIFICADA("Desqualificada"); //Denúncia não atende ao propósito

   private final String descricao;
}
