package br.com.oversight.ambienta.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumStatusDenuncia {

    PENDENTE_ORGAO("Pendente"), RECEBIDA("Recebida pelo órgão");

    private final String descricao;
}