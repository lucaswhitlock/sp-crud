/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.validate;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import java.util.regex.Pattern;
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
public class PessoaNomeInvalido {

    private Logger log = LogManager.getLogger(PessoaNomeInvalido.class);

    public void validar(@Observes Pessoa pessoa) throws Exception {
        log.debug("Validando nome invalido!");

        if (pessoa.getNome() == null || Pattern.compile("(.)*(\\d)(.)*").matcher(pessoa.getNome()).matches()) {
            throw new ObserverException("Nome informado inválido!");
        }
    }
}
