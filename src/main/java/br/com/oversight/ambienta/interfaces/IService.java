package br.com.oversight.ambienta.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface genérica para camada de serviço
 *
 * @param <T>
 * @param <N>
 */
public interface IService<T, N> {

   T create(T entity);

   List<T> read();

   T read(N id);

   Page<T> read(String nome, Pageable pageable);

   void update(T entity);

   void delete(N id);

}
