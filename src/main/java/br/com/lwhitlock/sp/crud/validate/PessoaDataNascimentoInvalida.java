package br.com.lwhitlock.sp.crud.validate;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.ObserverException;
import javax.enterprise.event.Observes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lwhitlock
 */
@RequestScoped
public class PessoaDataNascimentoInvalida {

    private Logger log = LoggerFactory.getLogger(PessoaDataNascimentoInvalida.class);

    public void validar(@Observes Pessoa pessoa) throws Exception {
        log.debug("Validando data de nascimento invalida");

        if (pessoa.getDataNascimento() == null) {
            throw new ObserverException("Data de nascimento informada invalida!");
        }
    }
}
