/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.validate;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import java.util.Arrays;
import java.util.List;
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
public class PessoaCPFInvalido {

    private Logger log = LogManager.getLogger(PessoaCPFInvalido.class);

    private final List<String> invalidos = Arrays.asList("00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999");

    public void validar(@Observes Pessoa pessoa) throws Exception {
        log.debug("Validando pessoa com cpf invalido");

        if (!isValid(pessoa.getCpf())) {
            throw new ObserverException("CPF invalido!");
        }
    }

    private boolean isValid(String cpf) {
        if (nuloOuVazio(cpf)) {
            return false;
        }

        String ncpf = cpf.trim();
        if (ncpf.length() != 11 || Long.valueOf(ncpf).equals(0L) || invalidos.contains(ncpf)) {
            return false;
        }

        int d1 = 0, d2 = 0, digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        for (int nCount = 1; nCount < ncpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(ncpf.substring(nCount - 1, nCount));
            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;
            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);
        digito1 = (resto < 2 ? 0 : (11 - resto));
        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);
        digito2 = (resto < 2 ? 0 : (11 - resto));

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = ncpf.substring(ncpf.length() - 2, ncpf.length());
        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
    }

    private Boolean nuloOuVazio(String string) {
        return (string != null && string.trim().length() > 0) ? Boolean.FALSE : Boolean.TRUE;
    }
}
