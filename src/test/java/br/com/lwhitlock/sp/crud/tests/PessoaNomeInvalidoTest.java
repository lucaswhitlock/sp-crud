/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.tests;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import br.com.lwhitlock.sp.crud.validate.PessoaNomeInvalido;
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
public class PessoaNomeInvalidoTest {

    @Inject
    Pessoa pessoa;

    @Inject
    PessoaNomeInvalido validacao;

    @InRequestScope
    @Test(expected = Exception.class)
    public void verificarPessoaComNomeInvalido() throws Exception {
        pessoa.setNome("123456789");
        validacao.validar(pessoa);
    }

    @InRequestScope
    @Test
    public void verificarPessoaComNomeValido() throws Exception {
        pessoa.setNome("Lucas Eduardo Whitlock");
        validacao.validar(pessoa);
    }
}
