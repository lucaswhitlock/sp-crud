/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.validate;

import br.com.lwhitlock.sp.crud.dao.DAO;
import br.com.lwhitlock.sp.crud.entity.Pessoa;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObserverException;
import javax.inject.Inject;

/**
 *
 * @author lwhitlock
 */
@RequestScoped
public class AgrupadoraValidacoes {

    @Inject
    private Event<Pessoa> eventPessoa;

    public void validar(Pessoa pessoa, DAO dao, Boolean isUpdate) throws Exception {
        try {
            eventPessoa.fire(pessoa);
            if (!isUpdate && dao.pessoaJaExistente(pessoa)) {
                throw new Exception("Pessoa informada já existente!");
            }
        } catch (ObserverException e) {
            throw new Exception(e.getCause());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
