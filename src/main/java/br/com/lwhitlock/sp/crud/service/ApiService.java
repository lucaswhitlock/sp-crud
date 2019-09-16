/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.service;

import br.com.lwhitlock.sp.crud.dao.DAO;
import br.com.lwhitlock.sp.crud.entity.Login;
import br.com.lwhitlock.sp.crud.entity.Pessoa;
import br.com.lwhitlock.sp.crud.validate.AgrupadoraValidacoes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lwhitlock
 */
@Stateless
@LocalBean
public class ApiService {

    @EJB
    private DAO dao;

    @Inject
    private AgrupadoraValidacoes agrupadoraValidacoes;

    public void cadastrarPessoa(Pessoa pessoa) throws Exception {
        agrupadoraValidacoes.validar(pessoa, dao, Boolean.FALSE);
        //Atualiza datas obrigatórias
        pessoa.setDataAtualizacao(new Date());
        if (pessoa.getDataCadastro() == null) {
            pessoa.setDataCadastro(new Date());
        }
        dao.inserir(pessoa);
    }

    public List<Pessoa> buscarPessoaPorId(Long id) throws Exception {
        Pessoa pessoaEncontrada = dao.buscarPessoaPorId(id);
        List<Pessoa> resultList = new ArrayList<>();
        resultList.add(pessoaEncontrada);
        return resultList;
    }

    public void atualizarPessoa(Pessoa pessoa) throws Exception {
        agrupadoraValidacoes.validar(pessoa, dao, Boolean.TRUE);
        pessoa.setDataAtualizacao(new Date());
        dao.atualizar(pessoa);
    }

    public void removerPessoa(Long id) throws Exception {
        Pessoa pessoa = dao.buscarPessoaPorId(id);
        if (pessoa != null) {
            dao.excluir(pessoa);
        } else {
            throw new Exception("Pessoa informada nao encontrada!");
        }
    }

    public Boolean login(Login login) throws Exception {
        Pessoa pessoa = dao.buscarPessoaPorCPF(login.getCpf());
        if (pessoa != null && !pessoa.getSenha().equals(login.getSenha())) {
            throw new Exception("Senha incorreta!");
        } else if (pessoa == null) {
            throw new Exception("Pessoa não encontrada!");
        } else {
            return Boolean.TRUE;
        }
    }
}
