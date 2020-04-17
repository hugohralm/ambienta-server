package br.com.oversight.ambienta.service;

import br.com.oversight.ambienta.interfaces.IService;
import br.com.oversight.ambienta.model.Orgao;
import br.com.oversight.ambienta.repository.OrgaoRepository;
import br.com.oversight.ambienta.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class OrgaoService implements IService<Orgao, Integer> {

   @Autowired
   private OrgaoRepository repository;

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Override
   public Orgao create(Orgao orgao) {
      if (orgao.getGestor() != null) {
         usuarioRepository.findOneWithPapelByCpf(orgao.getGestor().getCpf()).ifPresent(orgao::setGestor);
      }
      return repository.save(orgao);
   }

   @Override
   public List<Orgao> read() {
      return repository.findAll();
   }

   @Override
   public Orgao read(Integer id) {
      return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Órgão %d", id)));
   }

   @Override
   public Page<Orgao> read(String nome, Pageable pageable) {
      if (StringUtils.hasText(nome)) {
         return repository.findByNomeContainingIgnoreCaseOrderByNome(nome, pageable);
      } else {
         return repository.findAll(pageable);
      }
   }

   @Override
   public void update(Orgao orgao) {
      if (orgao.getGestor() != null) {
         usuarioRepository.findOneWithPapelByCpf(orgao.getGestor().getCpf()).ifPresent(orgao::setGestor);
      }
      repository.save(orgao);
   }

   @Override
   public void delete(Integer id) {
      repository.deleteById(id);
   }
}
