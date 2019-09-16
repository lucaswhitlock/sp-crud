package br.com.lwhitlock.sp.crud.dao;

import br.com.lwhitlock.sp.crud.abstracts.AbstractDAO;
import br.com.lwhitlock.sp.crud.entity.Pessoa;
import br.com.lwhitlock.sp.crud.type.TypeQuery;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author lwhitlock
 */
@Stateless
@LocalBean
public class DAO extends AbstractDAO {

    private Logger log = LogManager.getLogger(DAO.class);

    @Override
    @PersistenceContext(unitName = "default-pu")
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Boolean pessoaJaExistente(Pessoa pessoa) throws Exception {
        List<Pessoa> obj = null;
        try {
            obj = (List<Pessoa>) buscarLista("Pessoa.porCPF", TypeQuery.NAMED_QUERY, pessoa.getCpf());
        } catch (Exception e) {
            log.error("Falha ao buscar pessoa com CPF {}! Erro: {}", pessoa.getCpf(), e.getMessage());
        }
        return obj.size() > 0;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Pessoa buscarPessoaPorId(Long id) throws Exception {
        Pessoa obj = null;
        try {
            obj = (Pessoa) buscarEntidade("Pessoa.porId", TypeQuery.NAMED_QUERY, id);
        } catch (Exception e) {
            log.error("Falha ao buscar pessoa com ID {}! Erro: {}", id, e.getMessage());
        }
        return obj;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Pessoa buscarPessoaPorCPF(String cpf) throws Exception {
        Pessoa obj = null;
        try {
            obj = (Pessoa) buscarEntidade("Pessoa.porCPF", TypeQuery.NAMED_QUERY, cpf);
        } catch (Exception e) {
            log.error("Falha ao buscar pessoa com CPF {}! Erro: {}", cpf, e.getMessage());
        }
        return obj;
    }
}
