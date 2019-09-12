package br.com.lwhitlock.sp.crud.abstracts;

import br.com.lwhitlock.sp.crud.type.TypeQuery;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lwhitlock
 * @param <T>
 */
public interface IAbstractDAO<T> extends Serializable {

    T inserir(T entity) throws Exception;

    T atualizar(T entity) throws Exception;

    void excluir(T entity) throws Exception;

    T buscarEntidade(String command, TypeQuery type, Object... params) throws Exception;

    List<T> buscarLista(String command, TypeQuery type, Object... params) throws Exception;
}
