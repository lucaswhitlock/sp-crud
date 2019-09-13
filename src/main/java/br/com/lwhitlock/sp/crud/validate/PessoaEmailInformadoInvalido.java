/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.validate;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.ObserverException;
import javax.enterprise.event.Observes;
import org.apache.commons.validator.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author lwhitlock
 */
@RequestScoped
public class PessoaEmailInformadoInvalido {

    private Logger log = LogManager.getLogger(PessoaEmailInformadoInvalido.class);

    public void validar(@Observes Pessoa pessoa) throws Exception {
        log.debug("Validando pessoa com email invalido informado");

        if (!nuloOuVazio(pessoa.getEmail())
                && !EmailValidator.getInstance().isValid(pessoa.getEmail())) {
            throw new ObserverException("Email invalido!");
        }
    }

    private Boolean nuloOuVazio(String string) {
        return (string != null && string.trim().length() > 0) ? Boolean.FALSE : Boolean.TRUE;
    }

}
