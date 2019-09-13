package br.com.lwhitlock.sp.crud.validate;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.ObserverException;
import javax.enterprise.event.Observes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author lwhitlock
 */
@RequestScoped
public class PessoaDataNascimentoInvalida {

    private Logger log = LogManager.getLogger(PessoaDataNascimentoInvalida.class);

    public void validar(@Observes Pessoa pessoa) throws Exception {
        log.debug("Validando data de nascimento invalida");

        if (pessoa.getDataNascimento() == null) {
            throw new ObserverException("Data de nascimento informada invalida!");
        }
    }
}
