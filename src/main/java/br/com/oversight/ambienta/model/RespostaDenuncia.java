package br.com.oversight.ambienta.model;

import br.com.oversight.ambienta.model.enums.EnumStatusDenuncia;
import br.com.oversight.ambienta.security.model.Usuario;
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

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class RespostaDenuncia implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro;

   @Enumerated(EnumType.STRING)
   @NotNull(message = "O campo status é obrigatório")
   @Column(length = 40, nullable = false)
   private EnumStatusDenuncia status;

   @NotBlank(message = "Informe a resposta para a denúncia.")
   @Length(max = 2048, message = "O limite do campo resposta é de 2048 caracteres.")
   @Column(length = 2048, nullable = false, updatable = false)
   private String descricao;

   @ManyToOne
   @NotNull(message = "O campo denúncia é obrigatório")
   @JoinColumn(nullable = false, updatable = false)
   private Denuncia denuncia;

   @ManyToOne
   @JoinColumn(updatable = false)
   private Usuario usuario;

   @PrePersist
   private void prePersist() {
      this.dataCadastro = new Date();
   }
}
