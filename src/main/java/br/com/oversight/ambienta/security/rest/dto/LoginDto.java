package br.com.oversight.ambienta.security.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for storing a user's credentials.
 */
@Getter
@Setter
@ToString
public class LoginDto {

   @NotNull
   @Size(min = 1, max = 50)
   private String cpf;

   @NotNull
   @Size(min = 4, max = 100)
   private String senha;
}
