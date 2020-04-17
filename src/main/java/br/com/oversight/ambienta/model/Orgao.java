package br.com.oversight.ambienta.model;

import br.com.oversight.ambienta.security.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Orgao implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "ID")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORGAO_SEQ")
   @SequenceGenerator(name = "ORGAO_SEQ", sequenceName = "ORGAO_SEQ", allocationSize = 1)
   private Integer id;

   @Column(name = "DataCadastro", nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro;

   @NotBlank(message = "Informe o nome do orgão.")
   @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
   @Column(name = "Nome", nullable = false)
   private String nome;

   @ManyToOne
   private Usuario gestor;

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
      Orgao user = (Orgao) o;
      return id.equals(user.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
