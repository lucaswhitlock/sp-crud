package br.com.lwhitlock.sp.crud.abstracts;

import br.com.lwhitlock.sp.crud.type.TypeQuery;
import static br.com.lwhitlock.sp.crud.type.TypeQuery.NAMED_QUERY;
import static br.com.lwhitlock.sp.crud.type.TypeQuery.NATIVE_QUERY;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author lwhitlock
 * @param <T>
 */
public abstract class AbstractDAO<T> implements IAbstractDAO<T> {

    protected EntityManager entityManager;

    protected abstract void setEntityManager(EntityManager entityManager);

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T inserir(T entidade) throws Exception {
        try {
            getEntityManager().persist(entidade);
            return entidade;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public T atualizar(T entidade) throws Exception {
        try {
            return getEntityManager().merge(entidade);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void excluir(T entidade) throws Exception {
        try {
            getEntityManager().remove(getEntityManager().merge(entidade));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public T buscarEntidade(String command, TypeQuery type, Object... params) throws Exception {
        try {
            Query query = getQuery(type, command);
            setParameters(query, type, params);
            return (T) query.getSingleResult();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<T> buscarLista(String command, TypeQuery type, Object... params) throws Exception {
        try {

            Query query = getQuery(type, command);
            setParameters(query, type, params);
            return (List<T>) query.getResultList();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private Query getQuery(TypeQuery tq, String query) {
        switch (tq) {
            case NAMED_QUERY:
            case NAMED_NATIVE_QUERY:
                return getEntityManager().createNamedQuery(query);
            case NATIVE_QUERY:
                return getEntityManager().createNativeQuery(query);
            case HQL_QUERY:
                return getEntityManager().createQuery(query);
            default:
                return null;
        }
    }

    private void setParameters(Query query, TypeQuery tq, Object... params) {
        switch (tq) {
            case NATIVE_QUERY:
            case NAMED_NATIVE_QUERY:
                setParameters2(query, params);
                break;
            case NAMED_QUERY:
            case HQL_QUERY:
                setParameters1(query, params);
                break;
        }
    }

    public static void setParameters1(Query query, Object... params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    query.setParameter("p" + i, params[i]);
                }
            }
        }
    }

    public static void setParameters2(Query query, Object... params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    query.setParameter((i + 1), params[i]);
                }
            }
        }
    }
}
