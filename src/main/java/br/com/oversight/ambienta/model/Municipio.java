package br.com.oversight.ambienta.model;

import br.com.oversight.ambienta.model.enums.EnumUf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Municipio implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "ID")
   @GeneratedValue(generator = "MUNICIPIO_SEQ", strategy = GenerationType.SEQUENCE)
   @SequenceGenerator(name = "MUNICIPIO_SEQ", sequenceName = "MUNICIPIO_SEQ", allocationSize = 1, initialValue = 1)
   private Integer id;

   @Column(nullable = false, updatable = false)
   @NotBlank(message = "Informe o nome do municipio.")
   @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
   private String nome;

   @Enumerated(EnumType.STRING)
   @Column(length = 2, nullable = false, updatable = false)
   private EnumUf uf;

   @Column(nullable = false, updatable = false)
   private Integer codigoIbge;

   @Override
   public String toString() {
      return String.format("%s - %s", nome, uf);
   }
}
