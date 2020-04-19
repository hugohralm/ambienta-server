package br.com.oversight.ambienta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Categoria implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro;

   @JsonIgnore
   @ManyToOne
   @NotNull(message = "Informe tipo de categoria.")
   private TipoCategoria tipo;

   @NotBlank(message = "Informe o nome da categoria.")
   @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
   @Column(nullable = false)
   private String nome;

   @ManyToOne(optional = false)
   @NotNull(message = "Informe o órgão gestor da categoria.")
   private Orgao orgao;

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
      Categoria user = (Categoria) o;
      return id.equals(user.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
