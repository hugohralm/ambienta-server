package br.com.oversight.ambienta.service;

import br.com.oversight.ambienta.interfaces.IService;
import br.com.oversight.ambienta.model.TipoCategoria;
import br.com.oversight.ambienta.repository.TipoCategoriaRepository;
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
public class TipoCategoriaService implements IService<TipoCategoria, Integer> {

   @Autowired
   private TipoCategoriaRepository repository;

   @Override
   public TipoCategoria create(TipoCategoria tipoCategoria) {
      return repository.save(tipoCategoria);
   }

   @Override
   public List<TipoCategoria> read() {
      return repository.findAll();
   }

   @Override
   public TipoCategoria read(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException(String.format("Tipo categoria %d", id)));
   }

   @Override
   public Page<TipoCategoria> read(String nome, Pageable pageable) {
      if (StringUtils.hasText(nome)) {
         return repository.findByNomeContainingIgnoreCaseOrderByNome(nome, pageable);
      } else {
         return repository.findAll(pageable);
      }
   }

   @Override
   public void update(TipoCategoria tipoCategoria) {
      repository.save(tipoCategoria);
   }

   @Override
   public void delete(Integer id) {
      repository.deleteById(id);
   }
}
