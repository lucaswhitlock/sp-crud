package br.com.lwhitlock.sp.crud.entity;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lwhitlock
 */
@Entity
@Table(name = "sp_pessoas")
@NamedQueries({
    @NamedQuery(name = "Pessoa.porCPF", query = "SELECT obj FROM Pessoa obj WHERE obj.cpf = :p0"),
    @NamedQuery(name = "Pessoa.porId", query = "SELECT obj FROM Pessoa obj WHERE obj.id = :p0")
})
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sexo", nullable = true)
    private String sexo;

    @Column(name = "email", nullable = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "naturalidade", nullable = true)
    private String naturalidade;

    @Column(name = "nacionalidade", nullable = true)
    private String nacionalidade;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataCadastro", nullable = false)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataAtualizacao", nullable = false)
    private Date dataAtualizacao;

    @Column(name = "senha", nullable = false)
    private String senha;

    public Pessoa() {
        this.dataAtualizacao = new Date();
    }

    public Pessoa(Long id, String nome, String sexo, String email, Date dataNascimento, String naturalidade, String nacionalidade, String cpf, Date dataCadastro, String senha) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = new Date();
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getSenha() {
        return new String(Base64.getDecoder().decode(this.senha));
    }

    public void setSenha(String senha) {
        this.senha = Base64.getEncoder().encodeToString(senha.getBytes());
    }

}
