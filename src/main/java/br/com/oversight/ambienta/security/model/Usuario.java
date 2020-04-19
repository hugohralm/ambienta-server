package br.com.oversight.ambienta.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "USUARIO")
public class Usuario {

   @JsonIgnore
   @Id
   @Column(name = "ID")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "DataCadastro", nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCadastro;

   @CPF(message = "CPF inválido")
   @Column(name = "CPF", length = 11, unique = true, updatable = false)
   @NotNull
   @Size(min = 11, max = 11)
   private String cpf;

   @JsonIgnore
   @Column(name = "SENHA", length = 100)
   @NotNull
   @Size(min = 4, max = 100)
   private String senha;

   @Column(name = "NOME", length = 255)
   @NotNull
   @Size(min = 10, max = 255)
   private String nome;

   @Email(message = "Email inválido")
   @Length(max = 255, message = "O limite do campo email é de 255 caracteres.")
   @Column(length = 255)
   private String email;

   @JsonIgnore
   @Column(name = "ATIVADO", nullable = false)
   @NotNull
   private boolean ativado = false;

   @ManyToMany(cascade = CascadeType.PERSIST)
   @JoinTable(name = "USUARIO_PAPEL", joinColumns = {
      @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
      @JoinColumn(name = "PAPEL_NOME", referencedColumnName = "NOME")})
   @BatchSize(size = 20)
   private Set<Papel> papeis = new HashSet<>();

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
      Usuario user = (Usuario) o;
      return id.equals(user.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
