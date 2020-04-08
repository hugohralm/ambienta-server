package br.com.oversight.ambienta.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.oversight.ambienta.interfaces.IService;
import br.com.oversight.ambienta.model.Categoria;
import br.com.oversight.ambienta.repository.CategoriaRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CategoriaService implements IService<Categoria, Integer> {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public Categoria create(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public List<Categoria> read() {
        return repository.findAll();
    }

    @Override
    public Categoria read(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Categoria %d", id)));
    }

    @Override
    public Page<Categoria> read(String nome, Pageable pageable) {
        if (StringUtils.hasText(nome)) {
            return repository.findByNomeContainingIgnoreCaseOrderByNome(nome, pageable);
        } else {
            return repository.findAll(pageable);
        }
    }

    @Override
    public void update(Categoria categoria) {
        repository.save(categoria);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
