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
import br.com.oversight.ambienta.model.Orgao;
import br.com.oversight.ambienta.repository.OrgaoRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
public class OrgaoService implements IService<Orgao, Integer> {

    @Autowired
    private OrgaoRepository repository;

    @Override
    public Orgao create(Orgao orgao) {
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
        repository.save(orgao);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
