package br.com.oversight.ambienta.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Length(min = 0, max = 255, message = "O limite do campo nome é de 255 caracteres.")
    @Column(name = "Nome", length = 255, nullable = false)
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
