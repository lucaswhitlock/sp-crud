/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.tests;

import br.com.lwhitlock.sp.crud.abstracts.AbstractTest;
import br.com.lwhitlock.sp.crud.entity.Pessoa;
import br.com.lwhitlock.sp.crud.validate.PessoaDataNascimentoInvalida;
import java.util.Date;
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
public class PessoaDataNascimentoInvalidaTest extends AbstractTest {

    @Inject
    Pessoa pessoa;

    @Inject
    PessoaDataNascimentoInvalida validacao;

    @InRequestScope
    @Test(expected = Exception.class)
    public void verificarPessoaComDataDeNascimentoInvalida() throws Exception {
        validacao.validar(pessoa);
    }

    @InRequestScope
    @Test
    public void verificarPessoaComDataDeNascimentoValida() throws Exception {
        pessoa.setDataNascimento(new Date());
        validacao.validar(pessoa);
    }
}
