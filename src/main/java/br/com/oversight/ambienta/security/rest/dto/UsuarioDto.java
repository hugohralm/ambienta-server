package br.com.oversight.ambienta.security.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuarioDto {

    @CPF(message = "CPF inválido.")
    @Length(max = 11, message = "O limite do campo LOGIN do sistema é de 11 caracteres.")
    private String cpf;

    @Size(min = 5, max = 100)
    @NotBlank(message = "O campo senha e obrigatório.")
    @Length(min = 5, message = "O limite mínimo do campo SENHA do sistema é de 5 caracteres.")
    @Length(max = 100, message = "O limite do campo SENHA do sistema é de 100 caracteres.")
    private String senha;

    @NotBlank(message = "O campo nome e obrigatório.")
    @Length(max = 200, message = "O limite do campo NOME do usuário é de 200 caracteres.")
    private String nome;

    @Email(message = "Email inválido.")
    @NotBlank(message = "O campo e-mail e obrigatório.")
    @Length(max = 100, message = "O limite do campo E-MAIL é de 100 caracteres.")
    private String email;
}
