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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lwhitlock
 */
@RequestScoped
public class PessoaNomeInvalido {

    private Logger log = LoggerFactory.getLogger(PessoaNomeInvalido.class);

    public void validar(@Observes Pessoa pessoa) throws Exception {
        log.debug("Validando nome invalido!");

        if (pessoa.getNome() == null || pessoa.getNome().matches("[0-9]")) {
            throw new ObserverException("Nome informado inválido!");
        }
    }
}
