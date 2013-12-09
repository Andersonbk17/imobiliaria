/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.domainModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author emerson
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    
    @ManyToOne
    private Profissao profissao;
    
    private String conjugueNome;
    private String conjugueCpf;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date conjugueDataNascimento;
    
    private int conjugueSexo;
    private int conjugueRg;
    private String conjugueOrgaoExpedidor;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCasamento;
    private String conjugueTelefone;
    private String conjugueEmail;
    
    
    @ManyToOne
    private Profissao conjugueProfissao;
    
    @ManyToOne
    private Nacionalidade conjugueNacionalidade;
    
    private int clienteJuridicoFisico;
    private String JuridicoInscricaoMunicipal;
    private String JuridicoInscricaoEstadual;
    private boolean ativo;

    public Cliente() {
        this.dataCadastro = new Date();
        this.profissao = null;
        this.conjugueNome = null;
        this.conjugueCpf = null;
        this.conjugueDataNascimento = null;
        this.conjugueOrgaoExpedidor = null;
        this.dataCasamento = null;
        this.conjugueTelefone = null;
        this.conjugueEmail = null;
        this.conjugueProfissao = null;
        this.conjugueNacionalidade = null;
        this.JuridicoInscricaoMunicipal = null;
        this.JuridicoInscricaoEstadual = null;
        this.ativo = true;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public String getConjugueNome() {
        return conjugueNome;
    }

    public void setConjugueNome(String conjugueNome) {
        this.conjugueNome = conjugueNome;
    }

    public String getConjugueCpf() {
        return conjugueCpf;
    }

    public void setConjugueCpf(String conjugueCpf) {
        this.conjugueCpf = conjugueCpf;
    }

    public Date getConjugueDataNascimento() {
        return conjugueDataNascimento;
    }

    public void setConjugueDataNascimento(Date conjugueDataNascimento) {
        this.conjugueDataNascimento = conjugueDataNascimento;
    }

    public int getConjugueSexo() {
        return conjugueSexo;
    }

    public void setConjugueSexo(int conjugueSexo) {
        this.conjugueSexo = conjugueSexo;
    }

    public int getConjugueRg() {
        return conjugueRg;
    }

    public void setConjugueRg(int conjugueRg) {
        this.conjugueRg = conjugueRg;
    }

    public String getConjugueOrgaoExpedidor() {
        return conjugueOrgaoExpedidor;
    }

    public void setConjugueOrgaoExpedidor(String conjugueOrgaoExpedidor) {
        this.conjugueOrgaoExpedidor = conjugueOrgaoExpedidor;
    }

    public Date getDataCasamento() {
        return dataCasamento;
    }

    public void setDataCasamento(Date dataCasamento) {
        this.dataCasamento = dataCasamento;
    }

    public String getConjugueTelefone() {
        return conjugueTelefone;
    }

    public void setConjugueTelefone(String conjugueTelefone) {
        this.conjugueTelefone = conjugueTelefone;
    }

    public String getConjugueEmail() {
        return conjugueEmail;
    }

    public void setConjugueEmail(String conjugueEmail) {
        this.conjugueEmail = conjugueEmail;
    }

    public Profissao getConjugueProfissao() {
        return conjugueProfissao;
    }

    public void setConjugueProfissao(Profissao conjugueProfissao) {
        this.conjugueProfissao = conjugueProfissao;
    }

    public Nacionalidade getConjugueNacionalidade() {
        return conjugueNacionalidade;
    }

    public void setConjugueNacionalidade(Nacionalidade conjugueNacionalidade) {
        this.conjugueNacionalidade = conjugueNacionalidade;
    }

    public int getClienteJuridicoFisico() {
        return clienteJuridicoFisico;
    }

    public void setClienteJuridicoFisico(int clienteJuridicoFisico) {
        this.clienteJuridicoFisico = clienteJuridicoFisico;
    }

    public String getJuridicoInscricaoMunicipal() {
        return JuridicoInscricaoMunicipal;
    }

    public void setJuridicoInscricaoMunicipal(String JuridicoInscricaoMunicipal) {
        this.JuridicoInscricaoMunicipal = JuridicoInscricaoMunicipal;
    }

    public String getJuridicoInscricaoEstadual() {
        return JuridicoInscricaoEstadual;
    }

    public void setJuridicoInscricaoEstadual(String JuridicoInscricaoEstadual) {
        this.JuridicoInscricaoEstadual = JuridicoInscricaoEstadual;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
