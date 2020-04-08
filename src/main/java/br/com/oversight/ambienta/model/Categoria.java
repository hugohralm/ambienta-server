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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_SEQ")
    @SequenceGenerator(name = "CATEGORIA_SEQ", sequenceName = "CATEGORIA_SEQ", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @ManyToOne
    @NotNull(message = "Informe tipo de categoria.")
    private TipoCategoria tipo;

    @NotBlank(message = "Informe o nome da categoria.")
    @Length(min = 0, max = 255, message = "O limite do campo nome é de 255 caracteres.")
    @Column(name = "Nome", length = 255, nullable = false)
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
