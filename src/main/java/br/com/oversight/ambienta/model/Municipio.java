package br.com.oversight.ambienta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import br.com.oversight.ambienta.model.enums.EnumUf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Municipio {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "MUNICIPIO_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "MUNICIPIO_SEQ", sequenceName = "MUNICIPIO_SEQ", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(nullable = false, length = 255, updatable = false)
    @NotBlank(message = "Informe o nome do municipio.")
    @Length(min = 0, max = 255, message = "O limite do campo nome é de 255 caracteres.")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(length = 2, nullable = false, updatable = false)
    private EnumUf uf;

    @Column(nullable = false, updatable = false)
    private Integer codigoIbge;

    @Override
    public String toString() {
        return String.format("%s - %s", nome, uf);
    }
}
