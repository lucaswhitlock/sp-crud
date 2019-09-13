/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.tests;

import br.com.lwhitlock.sp.crud.abstracts.AbstractTest;
import br.com.lwhitlock.sp.crud.entity.Pessoa;
import br.com.lwhitlock.sp.crud.validate.PessoaCPFInvalido;
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
public class PessoaCPFInvalidoTest extends AbstractTest {

    @Inject
    Pessoa pessoa;

    @Inject
    PessoaCPFInvalido validacao;

    @InRequestScope
    @Test(expected = Exception.class)
    public void verificarPessoaComCPFInvalido() throws Exception {
        pessoa.setCpf("00000000000");
        validacao.validar(pessoa);
    }

    @InRequestScope
    @Test
    public void verificarPessoaComCPFValido() throws Exception {
        pessoa.setCpf("05127376148");
        validacao.validar(pessoa);
    }
}
