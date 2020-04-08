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
public class DenunciaAnonimaService {

    @Autowired
    private DenunciaRepository repository;

    public Denuncia create(Denuncia denuncia) {
        denuncia.setDenunciante(null);
        return repository.save(denuncia);
    }

    public List<Denuncia> read() {
        return repository.findByDenuncianteIsNull();
    }

    public Denuncia read(Integer id) {
        return repository.findByIdAndDenuncianteIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Denúncia %d", id)));
    }

    public Page<Denuncia> read(String titulo, Pageable pageable) {
        if (StringUtils.hasText(titulo)) {
            return repository.findByTituloContainingIgnoreCaseAndDenuncianteIsNullOrderByTitulo(titulo, pageable);
        } else {
            return repository.findByDenuncianteIsNull(pageable);
        }
    }

    public void update(Denuncia denuncia) {
        repository.save(denuncia);
    }
}
