package br.com.oversight.ambienta.model;

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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class TipoCategoria implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro;

   @NotBlank(message = "Informe o nome do tipo de categoria.")
   @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private boolean ativo = false;

   @OneToMany(mappedBy = "tipo")
   private Set<Categoria> categorias = new HashSet<>();

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
