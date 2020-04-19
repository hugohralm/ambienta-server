package br.com.oversight.ambienta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Evidencia implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro;

   @Column(nullable = false)
   private String idPublico;

   @Column(nullable = false)
   private String assinatura;

   @Column(nullable = false)
   private String formato;

   @Column(nullable = false)
   private String tipoArquivo;

   @Column(nullable = false)
   private String url;

   @ManyToOne(optional = true)
//   @NotNull(message = "Informe qual a denúncia.")
   private Denuncia denuncia;

   @PrePersist
   private void prePersist() {
      this.dataCadastro = new Date();
      this.formato = this.formato.toUpperCase();
      this.tipoArquivo = this.tipoArquivo.toUpperCase();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Evidencia user = (Evidencia) o;
      return id.equals(user.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
