package br.com.oversight.ambienta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class TipoCategoria {

   @Id
   @Column(name = "ID")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_CATEGORIA_SEQ")
   @SequenceGenerator(name = "TIPO_CATEGORIA_SEQ", sequenceName = "TIPO_CATEGORIA_SEQ", allocationSize = 1)
   private Integer id;

   @Column(name = "DataCadastro", nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro;

   @NotBlank(message = "Informe o nome do tipo de categoria.")
   @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
   @Column(name = "Nome", nullable = false)
   private String nome;

   @Column(nullable = false)
   private boolean ativo = false;

   @PrePersist
   private void prePersist() {
      this.dataCadastro = new Date();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      TipoCategoria user = (TipoCategoria) o;
      return id.equals(user.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
