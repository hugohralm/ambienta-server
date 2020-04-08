package br.com.oversight.ambienta.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.oversight.ambienta.security.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Orgao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORGAO_SEQ")
    @SequenceGenerator(name = "ORGAO_SEQ", sequenceName = "ORGAO_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "DataCadastro", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @NotBlank(message = "Informe o nome do orgão.")
    @Length(min = 0, max = 255, message = "O limite do campo nome é de 255 caracteres.")
    @Column(name = "Nome", length = 255, nullable = false)
    private String nome;

    @JsonIgnore
    @ManyToOne(optional = false)
    @NotNull(message = "Informe o usuário gestor do órgão.")
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
