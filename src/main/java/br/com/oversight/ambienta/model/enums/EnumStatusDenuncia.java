package br.com.oversight.ambienta.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumStatusDenuncia {

    AGUARDANDO_ANALISE("Aguardando Análise"), RECEBIDA("Recebida pelo órgão");

    private final String descricao;
}