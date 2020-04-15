package br.com.oversight.ambienta.security.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.oversight.ambienta.security.SecurityUtils;
import br.com.oversight.ambienta.security.model.Usuario;
import br.com.oversight.ambienta.security.repository.UsuarioRepository;
import br.com.oversight.ambienta.security.rest.dto.UsuarioDto;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UsuarioService {

   private final UsuarioRepository usuarioRepository;

   public UsuarioService(UsuarioRepository usuarioRepository) {
      this.usuarioRepository = usuarioRepository;
   }

   @Transactional(readOnly = true)
   public Optional<Usuario> getUserWithAuthorities() {
      return SecurityUtils.getCurrentUsername().flatMap(usuarioRepository::findOneWithPapelByCpf);
   }
   
   public Usuario create(UsuarioDto usuarioDto) {
       return null;
   }
}
