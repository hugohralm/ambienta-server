package br.com.oversight.ambienta.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

import br.com.oversight.ambienta.model.enums.EnumStatusDenuncia;
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
public class Denuncia {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DENUNCIA_SEQ")
    @SequenceGenerator(name = "DENUNCIA_SEQ", sequenceName = "DENUNCIA_SEQ", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo status é obrigatório")
    @Column(length = 40, nullable = false)
    private EnumStatusDenuncia status = EnumStatusDenuncia.PENDENTE_ORGAO;

    @NotBlank(message = "Informe o título da denúncia.")
    @Length(min = 0, max = 255, message = "O limite do campo título é de 255 caracteres.")
    @Column(length = 255, nullable = false, updatable = false)
    private String titulo;

    @ManyToOne(optional = false)
    @JoinColumn(updatable = false)
    @NotNull(message = "Informe a categoria da denúncia.")
    private Categoria categoria;

    @NotBlank(message = "Informe a descrição da denúncia.")
    @Length(min = 0, max = 2048, message = "O limite do campo descrição é de 2048 caracteres.")
    @Column(length = 2048, nullable = false, updatable = false)
    private String descricao;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOcorrido;

    @Length(min = 0, max = 255, message = "O limite do campo denunciado é de 2048 caracteres.")
    @Column(length = 255)
    private String denunciado;

    @ManyToOne
    @JoinColumn(updatable = false)
    private Usuario denunciante;

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
        Denuncia user = (Denuncia) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
