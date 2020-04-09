package br.com.oversight.ambienta.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.oversight.ambienta.model.Denuncia;
import br.com.oversight.ambienta.repository.DenunciaRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DenunciaService {

    @Autowired
    private DenunciaRepository repository;

    public Denuncia create(Denuncia orgao) {
        return repository.save(orgao);
    }

    public List<Denuncia> read() {
        return repository.findAll();
    }

    public Denuncia read(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Denúncia %d", id)));
    }

    public Denuncia readByCodigoAcompanhamento(String codigoAcompanhamento) {
        return repository.findByCodigoAcompanhamento(codigoAcompanhamento)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Denúncia %d", codigoAcompanhamento)));
    }

    public Page<Denuncia> read(String titulo, Pageable pageable) {
        if (StringUtils.hasText(titulo)) {
            return repository.findByTituloContainingIgnoreCaseOrderByTitulo(titulo, pageable);
        } else {
            return repository.findAll(pageable);
        }
    }

    public void update(Denuncia denuncia) {
        repository.save(denuncia);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
