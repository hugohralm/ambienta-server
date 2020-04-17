package br.com.oversight.ambienta.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EnderecoTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private String cep;
   private String logradouro;
   private String complemento;
   private String bairro;
   private String localidade;
   private String uf;
   private Integer ibge;
}
