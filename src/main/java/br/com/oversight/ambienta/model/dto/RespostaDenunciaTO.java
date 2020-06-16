package br.com.oversight.ambienta.model.dto;

import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.model.enums.EnumStatusDenuncia;
import lombok.Data;

import java.io.Serializable;

@Data
public class RespostaDenunciaTO implements Serializable {
   private EnumStatusDenuncia status;
   private String descricao;
   private Denuncia denuncia;
}
