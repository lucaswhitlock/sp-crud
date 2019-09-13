/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.tests;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import br.com.lwhitlock.sp.crud.validate.PessoaEmailInformadoInvalido;
import javax.inject.Inject;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author lwhitlock
 */
@RunWith(CdiRunner.class)
public class PessoaEmailInformadoInvalidoTest {

    @Inject
    Pessoa pessoa;

    @Inject
    PessoaEmailInformadoInvalido validacao;

    @InRequestScope
    @Test(expected = Exception.class)
    public void verificarPessoaComEmailInvalido() throws Exception {
        pessoa.setEmail("aaaaaaaaaaaaaaa");
        validacao.validar(pessoa);
    }

    @InRequestScope
    @Test
    public void verificarPessoaComEmailValido() throws Exception {
        pessoa.setEmail("le.whitlock.dev@gmail.com");
        validacao.validar(pessoa);
    }
}
